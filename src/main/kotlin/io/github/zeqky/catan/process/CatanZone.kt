package io.github.zeqky.catan.process

import org.bukkit.Location

class CatanZone() {
    lateinit var type: ZoneType
    lateinit var loc: Location
}

enum class ZoneType {
    ROAD,
    TOWN
}