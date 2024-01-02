package io.github.zeqky.catan.core

import io.github.zeqky.catan.core.dialog.GameDialogAdapter
import io.github.zeqky.catan.core.dialog.PlaceRoadAndTown
import kotlinx.coroutines.*
import java.util.*

class Game {
    val board = Board(this)
    val dialogAdapter = GameDialogAdapter()

    private lateinit var turns: List<GamePlayer>

    private lateinit var turnQueue: Queue<GamePlayer>

    lateinit var currentTurn: GamePlayer
        private set

    fun launch(scope: CoroutineScope, dispatcher: CoroutineDispatcher = Dispatchers.Default) {
        turns = arrayListOf<GamePlayer>().apply {
            addAll(board.players)
        }
        turnQueue = ArrayDeque()
        scope.launch(dispatcher) {

        }
    }
}