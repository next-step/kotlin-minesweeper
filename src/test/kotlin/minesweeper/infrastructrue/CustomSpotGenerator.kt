package minesweeper.infrastructrue

import minesweeper.domain.FieldInfo
import minesweeper.domain.MineCount
import minesweeper.domain.MineSpot
import minesweeper.domain.SafeSpot
import minesweeper.domain.Spot
import minesweeper.domain.SpotGenerator

class CustomSpotGenerator(private val minePositions: Set<Pair<Int, Int>>) : SpotGenerator {
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
        minePositions: Set<Pair<Int, Int>>,
    ): List<Spot> {
        return (0 until height).flatMap { x ->
            (0 until width).map { y ->
                createSpot(x, y, minePositions)
            }
        }
    }

    private fun createSpot(
        x: Int,
        y: Int,
        minePositions: Set<Pair<Int, Int>>,
    ): Spot {
        val position = Pair(x, y)

        if (minePositions.contains(position)) {
            return MineSpot(y, x)
        }
        return SafeSpot(y, x)
    }
}
