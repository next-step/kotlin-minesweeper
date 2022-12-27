package minesweeper.dto

import minesweeper.domain.Land
import minesweeper.domain.tile.Marking

@JvmInline
value class LandDto(private val land: Land) {
    fun getTiles(): List<String> {
        return land.getTiles().map { getMarkingAsString(it) }
    }

    fun getWidth() = land.getWidth()

    private fun getMarkingAsString(marking: Marking): String {
        return when (marking) {
            Marking.MINE -> "*"
            Marking.EMPTY -> "0"
            Marking.ONE -> "1"
            Marking.TWO -> "2"
            Marking.THREE -> "3"
            Marking.FOUR -> "4"
            Marking.FIVE -> "5"
            Marking.SIX -> "6"
            Marking.SEVEN -> "7"
            Marking.EIGHT -> "8"
            Marking.CLOSED -> "C"
        }
    }
}
