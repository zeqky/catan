package io.github.zeqky.catan.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Game {
    val board = Board(this)
    fun launch(scope: CoroutineScope) {
        scope.launch {

        }
    }
}