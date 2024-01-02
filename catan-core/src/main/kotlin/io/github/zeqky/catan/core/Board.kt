package io.github.zeqky.catan.core

class Board(val game: Game) {
    val zones = arrayListOf<GameZone>()
    val lands = arrayListOf<GameLand>()
    val players = arrayListOf<GamePlayer>()

    fun newZone(): GameZone {
        return GameZone().apply {
            zones += this
        }
    }

    fun newLand(): GameLand {
        return GameLand().apply {
            lands += this
        }
    }

    fun newPlayer(): GamePlayer {
        return GamePlayer().apply {
            players += this
        }
    }
}