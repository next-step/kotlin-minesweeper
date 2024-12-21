package minesweeper.infrastructure

import minesweeper.domain.FieldInfo
import minesweeper.domain.MineCount
import minesweeper.domain.MineSpot
import minesweeper.domain.SafeSpot
import minesweeper.domain.Spot
import minesweeper.domain.SpotGenerator

class RandomSpotGenerator : SpotGenerator {
    override fun generate(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): List<Spot> {
        val height = fieldInfo.getHeight()
        val width = fieldInfo.getWidth()
        val minePositions = extractMinePositions(mineCount, width, height)

        return createFieldSpots(height, width, minePositions)
    }

    private fun extractMinePositions(
        mineCount: MineCount,
        width: Int,
        height: Int,
    ): Set<Pair<Int, Int>> {
        val minePositions = mutableSetOf<Pair<Int, Int>>()
        while (minePositions.size < mineCount.count) {
            val position = generateRandomPosition(width, height)
            minePositions.add(position)
        }
        return minePositions
    }

    private fun generateRandomPosition(
        width: Int,
        height: Int,
    ): Pair<Int, Int> {
        val x = (0 until height).random()
        val y = (0 until width).random()
        return Pair(x, y)
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
