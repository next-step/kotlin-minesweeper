package minesweeper.infrastructrue

import minesweeper.domain.Coordinate
import minesweeper.domain.FieldInfo
import minesweeper.domain.MineCount
import minesweeper.domain.MineSpot
import minesweeper.domain.SafeSpot
import minesweeper.domain.Spot
import minesweeper.domain.SpotGenerator

class CustomSpotGenerator(private val minePositions: Set<Coordinate>) : SpotGenerator {
    override fun generate(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): List<Spot> {
        val height = fieldInfo.getHeight()
        val width = fieldInfo.getWidth()

        return createFieldSpots(height, width, minePositions)
    }

    private fun createFieldSpots(
        height: Int,
        width: Int,
        minePositions: Set<Coordinate>,
    ): List<Spot> {
        return (0 until height).flatMap { y ->
            (0 until width).map { x ->
                createSpot(x, y, minePositions)
            }
        }
    }

    private fun createSpot(
        x: Int,
        y: Int,
        minePositions: Set<Coordinate>,
    ): Spot {
        val position = Coordinate(x, y)

        if (minePositions.contains(position)) {
            return MineSpot(position)
        }
        return SafeSpot(position)
    }
}
