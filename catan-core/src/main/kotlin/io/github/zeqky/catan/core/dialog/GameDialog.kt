package io.github.zeqky.catan.core.dialog

import io.github.zeqky.catan.core.GamePlayer

abstract class GameDialog<R> {

    lateinit var default: () -> R
        private set

    internal fun initialize(default: () -> R) {
        this.default = default
    }
}

class PlaceRoadAndTown(val player: GamePlayer): GameDialog<Boolean>()