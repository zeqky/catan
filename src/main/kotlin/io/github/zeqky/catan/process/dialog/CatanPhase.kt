package io.github.zeqky.catan.process.dialog

import io.github.zeqky.catan.process.CatanPlayer

open class CatanPhase {

    open fun button() {

    }

    open fun timeout() {

    }

    open fun message() {

    }

    open suspend fun request(player: CatanPlayer): Any {
        return 0
    }
}