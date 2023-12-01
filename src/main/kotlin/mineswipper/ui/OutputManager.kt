package mineswipper.ui

import mineswipper.domain.map.Field
import mineswipper.domain.map.Mine
import mineswipper.domain.map.NormalPedal
import mineswipper.domain.map.Pedal

class OutputManager {
    fun printField(field: Field) {
        field.field.forEach {
            printWidth(it.value)
        }
    }

    private fun printWidth(pedals: List<Pedal>) {
        val pedal = pedals.joinToString(" ") {
            when (it) {
                is NormalPedal -> NORMAL_PEDAL
                is Mine -> MINE_PEDAL
            }
        }

        println(pedal)
    }

    fun printStartGame() {
        println(START_GAME)
    }

    companion object {
        private const val NORMAL_PEDAL: String = "c"
        private const val MINE_PEDAL: String = "*"
        private const val START_GAME: String = "지뢰찾기 게임 시작"
    }
}
