package io.github.zeqky.catan.process

import io.github.zeqky.catan.core.dialog.PlaceRoadAndTown

class GameDialogDispatcher {
    private lateinit var process: CatanProcess

    fun register(process: CatanProcess) {
        this.process = process

        process.game.dialogAdapter.apply {
            register(PlaceRoadAndTown::class.java, ::place)
        }
    }

    private suspend fun place(dialog: PlaceRoadAndTown): Boolean {
        return false
    }
}