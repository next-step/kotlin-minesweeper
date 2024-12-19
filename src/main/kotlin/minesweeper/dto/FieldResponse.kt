package minesweeper.dto

import minesweeper.domain.Field
import minesweeper.domain.SafeSpot

class FieldResponse(private val field: Field) {
    fun toFormattedStringInitialField(): String {
        return field.lines.joinToString("\n") { line ->
            line.spots.joinToString(" ") { spot ->
                if (spot.isMine()) {
                    "*"
                } else {
                    getNearbyMineCount(spot as SafeSpot)
                }.toString()
            }
        }
    }

    private fun getNearbyMineCount(spot: SafeSpot): String {
        return spot.nearbyMineCount.toString()
    }
}
