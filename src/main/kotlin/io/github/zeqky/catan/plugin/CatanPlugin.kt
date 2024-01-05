package io.github.zeqky.catan.plugin

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import io.github.zeqky.catan.process.CatanPreProcess
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class CatanPlugin : JavaPlugin() {
    var process: CatanPreProcess? = null
        private set

    override fun onEnable() {
        dataFolder.mkdirs()

        saveResource("lands.txt", false)
        saveResource("roads.txt", false)
        saveResource("towns.txt", false)
        saveDefaultConfig()

        kommand {
            "catan" {
                "start"("world" to dimension(), "players" to players()) {
                    executes {
                        val world: World by it
                        val players: Collection<Player> by it
                        startProcess(world, players.toSet())
                    }
                }
                "stop" {
                    executes {
                        stopProcess()
                    }
                }
            }
        }
    }

    private fun startProcess(world: World, players: Set<Player>) {
        require(process == null) { "Process is already running!" }
        process = CatanPreProcess(this, world).also { it.launch(players) }
    }

    private fun stopProcess() {
        val process = requireNotNull(process) { "Process is not running!" }
        process.stop()

        this.process = null
    }
}