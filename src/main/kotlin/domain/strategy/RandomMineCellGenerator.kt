package domain.strategy

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.Cell
import domain.Coordinate
import domain.MineSweeperMetric
import kotlin.random.Random

class RandomMineCellGenerator(private val mineSweeperMetric: MineSweeperMetric) : MineCellGenerator {
    override fun execute(): List<Cell> {
        val emptyCellCoordinates = createEmptyCells()
        val mineCellCoordinates = createMineCoordinate()

        val cells = emptyCellCoordinates.map { coordinate -> parseCell(mineCellCoordinates, coordinate) }
        return cells
    }

    private fun createEmptyCells(): Set<Coordinate> {
        val heightRange = MINIMUM_HEIGHT..mineSweeperMetric.mineBoardHeight
        val widthRange = MINIMUM_WIDTH..mineSweeperMetric.mineBoardWidth

        return heightRange.flatMap { height ->
            widthRange.map { width ->
                Coordinate(height, width)
            }
        }.toSet()
    }

    private fun createMineCoordinate(): Set<Coordinate> {
        val mineCells = mutableSetOf<Coordinate>()

        while (mineCells.size < mineSweeperMetric.mineCount) {
            val randomHeight = Random.nextInt(MINIMUM_HEIGHT, mineSweeperMetric.mineBoardHeight + 1)
            val randomWidth = Random.nextInt(MINIMUM_WIDTH, mineSweeperMetric.mineBoardWidth + 1)

            mineCells.add(Coordinate(randomHeight, randomWidth))
        }

        return mineCells
    }

    private fun parseCell(
        mineCoordinates: Set<Coordinate>,
        coordinate: Coordinate,
    ): Cell {
        if (coordinate in mineCoordinates) {
            return Cell.MineCell(coordinate)
        }
        return Cell.EmptyCell(coordinate)
    }
}
