package minesweeper.service

import minesweeper.model.Cell

private const val PREV_INDEX = -1
private const val NEXT_INDEX = 1
private const val HERE_INDEX = 0

object CellSelector {
    private val delta = listOf(PREV_INDEX, HERE_INDEX, NEXT_INDEX)
    private val deltaCells = delta.flatMap { x -> delta.map { y -> Cell(x, y) } }

    fun nearCellsOf(target: Cell): List<Cell> {
        return deltaCells.map { deltaCell -> target + deltaCell }
            .filterNot { it == target }
    }
}

private operator fun Cell.plus(other: Cell) = Cell(x + other.x, y + other.y)
