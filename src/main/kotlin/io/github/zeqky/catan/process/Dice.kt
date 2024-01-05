package io.github.zeqky.catan.process

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.ArmorStand
import org.bukkit.inventory.ItemStack
import java.io.File

class Dice(val process: CatanPreProcess, catanPlayer: CatanPlayer) {
    val spawnLoc: Location

    val dice = process.fakeEntityServer.spawnEntity(catanPlayer.player.location, ArmorStand::class.java).apply {
        updateMetadata {
            isInvisible = true
            isInvulnerable = true
        }
        updateEquipment {
            setHelmet(ItemStack(Material.PAPER).apply {
                                                      editMeta {
                                                          it.setCustomModelData(1)
                                                      }
            }, true)
        }
    }

    init {
        val yaml = YamlConfiguration.loadConfiguration(File(process.plugin.dataFolder, "config.yml")).getConfigurationSection("dice-loc")
        spawnLoc = Location(process.world, yaml?.getDouble("x")!!, yaml.getDouble("y"), yaml.getDouble("z"))
    }

    fun roll() {

    }

    fun update() {

    }
}