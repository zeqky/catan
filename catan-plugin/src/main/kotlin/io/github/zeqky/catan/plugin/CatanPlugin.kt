package io.github.zeqky.catan.plugin

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import io.github.zeqky.catan.process.CatanProcess
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class CatanPlugin : JavaPlugin() {
    var process: CatanProcess? = null

    override fun onEnable() {
        saveResource("lands.yml", false)
        saveResource("zones.yml", false)

        kommand {
            register("catan") {
                then("start") {
                    then("world" to dimension()) {
                        then("players" to players()) {
                            executes {
                                val world: World by it
                                val players: Set<Player> by it
                                startProcess(world, players)
                            }
                        }
                    }
                }
                then("stop") {
                    stopProcess()
                }
            }
        }
    }

    private fun startProcess(world: World, players: Set<Player>) {
        process = CatanProcess(this, world).apply {
            launch(players)
        }
    }

    private fun stopProcess() {
        process?.unregister()
    }
}