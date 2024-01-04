package io.github.zeqky.catan.process

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CatanProcess(val prep: CatanPreProcess) {
    fun launch(scope: CoroutineScope) {
        scope.launch {

        }
    }

    fun update() {
        prep.fakeEntityServer.update()
    }
}