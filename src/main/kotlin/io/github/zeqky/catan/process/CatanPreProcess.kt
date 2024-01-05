package io.github.zeqky.catan.process

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.tap.fake.FakeEntityServer
import io.github.zeqky.catan.plugin.CatanPlugin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.scheduler.BukkitTask
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class CatanPreProcess(val plugin: CatanPlugin, val world: World) {
    lateinit var scope: CoroutineScope
        private set

    lateinit var fakeEntityServer: FakeEntityServer
        private set

    lateinit var listener: CatanListener
        private set

    lateinit var process: CatanProcess
        private set

    lateinit var zones: List<CatanZone>

    lateinit var lands: List<CatanLand>

    lateinit var players: List<CatanPlayer>

    lateinit var task: BukkitTask

    val dices = arrayListOf<Dice>()


    fun launch(players: Set<Player>) {
        scope = HeartbeatScope()
        fakeEntityServer = FakeEntityServer.create(plugin)
        listener = CatanListener().apply {
            Bukkit.getPluginManager().registerEvents(this, plugin)
        }
        process = CatanProcess(this)

        task = Bukkit.getScheduler().runTaskTimer(plugin, process::update, 0L, 1L)

        initializeZones()
        initializeLands()
        initializePlayers(players)

        scope.launch {
            process.launch(scope)
        }
    }

    fun initializeZones() {
        val roadsFile = File(plugin.dataFolder, "roads.txt")
        val townsFile = File(plugin.dataFolder, "towns.txt")

        val reader1 = BufferedReader(FileReader(roadsFile))
        val reader2 = BufferedReader(FileReader(townsFile))

        val list = arrayListOf<CatanZone>()

        reader1.readLines().forEach {
            val s = it.split(" ")
            list += CatanZone().apply {
                type = ZoneType.ROAD
                loc = Location(world, s[0].toDouble(), -60.0, s[1].toDouble())
            }
        }

        reader2.readLines().forEach {
            val s = it.split(" ")
            list += CatanZone().apply {
                type = ZoneType.TOWN
                loc = Location(world, s[0].toDouble(), -60.0, s[1].toDouble())
            }
        }

        zones = list
    }

    fun initializeLands() {
        val landsFile = File(plugin.dataFolder, "lands.txt")

        val reader = BufferedReader(FileReader(landsFile))
        val list = arrayListOf<CatanLand>()

        reader.readLines().forEach {
            val s = it.split(" ")
            list += CatanLand().apply {
                loc = Location(world, s[0].toDouble(), -60.0, s[1].toDouble())
            }
        }

        lands = list
    }

    fun initializePlayers(players: Set<Player>) {
        val list = arrayListOf<CatanPlayer>()
        players.forEach {
            list += CatanPlayer(it)
        }

        this.players = list
    }

    fun spawnDice(player: CatanPlayer) {
        dices += Dice(this, player)
    }

    fun stop() {
        HandlerList.unregisterAll(plugin)
        fakeEntityServer.clear()
        scope.cancel()
        task.cancel()
    }
}