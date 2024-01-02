package io.github.zeqky.catan.process

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.zeqky.catan.core.Game
import io.github.zeqky.catan.plugin.CatanPlugin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class CatanProcess(val plugin: CatanPlugin, val world: World) {
    lateinit var scope: CoroutineScope
        private set

    lateinit var game: Game

    fun launch(players: Set<Player>) {
        scope = HeartbeatScope()
        game = Game()

        initializeZones()
        initializeLands()
        initializePlayers(players)

        scope.launch {
            game.launch(scope)
        }
    }

    fun initializeZones() {
        val file = File(plugin.dataFolder, "zones.yml")
        val reader = BufferedReader(FileReader(file))
        reader.lines().toList().forEachIndexed { index, s ->
            val string = s.split(' ')
            val x = string[0].toDouble()
            val z = string[1].toDouble()
            val y = -60.0
            val loc = Location(world, x, y, z)
            game.board.newZone().apply {
                attach(CatanZone().apply {
                    location = loc
                })
            }
        }
    }

    fun initializeLands() {
        val file = File(plugin.dataFolder, "lands.yml")
        val reader = BufferedReader(FileReader(file))
        reader.lines().toList().forEachIndexed { index, s ->
            val string = s.split(' ')
            val x = string[0].toDouble()
            val z = string[1].toDouble()
            val y = -60.0
            val loc = Location(world, x, y, z)
            game.board.newLand().apply {
                attach(CatanLand().apply {
                    location = loc
                })
            }
        }
    }

    fun initializePlayers(players: Set<Player>) {
        players.forEach {
            game.board.newPlayer().attach(CatanPlayer(it))
        }
    }

    fun unregister() {

    }
}