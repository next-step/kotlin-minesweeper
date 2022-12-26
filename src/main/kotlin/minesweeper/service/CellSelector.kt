package minesweeper.service

import minesweeper.model.Cell
import minesweeper.model.MineMap
import minesweeper.model.Mines
import minesweeper.model.getDefaultCellPool

private const val PREV_INDEX = -1
private const val NEXT_INDEX = 1
private const val HERE_INDEX = 0

object CellSelector {
    private val delta = listOf(PREV_INDEX, HERE_INDEX, NEXT_INDEX)
    private val deltaCells = delta.flatMap { x -> delta.map { y -> Cell(x, y) } }

    fun selectNearCellsOf(target: Cell): List<Cell> {
        return deltaCells.map { deltaCell -> target + deltaCell }
            .filterNot { it == target }
    }

    fun selectRandomCells(mineMap: MineMap, cellCount: Int): Mines {
        val shuffledMines = getDefaultCellPool(mineMap.rowSize, mineMap.columnSize)
            .keys
            .shuffled()
            .take(cellCount)
            .toSet()
        return Mines(shuffledMines)
    }
}

private operator fun Cell.plus(other: Cell) = Cell(x + other.x, y + other.y)
