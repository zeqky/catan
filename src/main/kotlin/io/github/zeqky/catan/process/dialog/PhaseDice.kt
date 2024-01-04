package io.github.zeqky.catan.process.dialog

import io.github.zeqky.catan.process.CatanPlayer

class PhaseDice: CatanPhase() {
    override suspend fun request(player: CatanPlayer): Int {
        //TODO 주사위 만든 후 업뎃 예정
        return 0
    }
}