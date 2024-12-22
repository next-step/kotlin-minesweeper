package minesweeper.dto

import minesweeper.domain.Field
import minesweeper.domain.Position
import minesweeper.domain.SafeSpot
import minesweeper.domain.Spot

class FieldResponse(private val field: Field) {
    fun toFormattedStringInitialField(): String {
        val fieldInfo = field.getFieldInfo()
        val positions = generatePositions(fieldInfo.getWidth(), fieldInfo.getHeight())

        return formatField(positions)
    }

    private fun generatePositions(
        width: Int,
        height: Int,
    ): List<Position> {
        return (1..width).flatMap { x ->
            (1..height).map { y ->
                Position(x, y)
            }
        }
    }

    private fun formatField(positions: List<Position>): String {
        return positions.groupBy { it.y }
            .map { (_, positionsInRow) ->
                positionsInRow.joinToString(" ") { position ->
                    formatSpot(field.getSpot(position))
                }
            }.joinToString("\n")
    }

    private fun formatSpot(spot: Spot): String {
        return when (spot) {
            is SafeSpot -> spot.nearbyMineCount.toString()
            else -> "*"
        }
    }
}
