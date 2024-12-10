package minesweeper.dto

import minesweeper.domain.Field

class FieldResponse(private val field: Field) {
    fun toFormattedStringInitialField(): String {
        return field.lines.joinToString("\n") { line ->
            line.spots.joinToString(" ") { spot ->
                if (spot.isMine()) "*" else "C"
            }
        }
    }
}
