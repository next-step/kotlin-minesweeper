package minesweeper

import minesweeper.domain.Area
import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.CellsGenerator
import minesweeper.domain.Coordinate
import minesweeper.domain.MineCount

fun OpenedCell(coordinate: Coordinate, aroundMineCount: Int = 0) =
    Cell.Block(coordinate, aroundMineCount).apply { open() }

class TestCellsGenerator(private val cells: List<Cell>) : CellsGenerator {
    constructor(vararg board: String) : this(
        board.flatMapIndexed { y, row ->
            row.mapIndexed { x, cell ->
                val coordinate = Coordinate(x, y)
                if (cell == '*') {
                    Cell.Mine(coordinate)
                } else {
                    val aroundMineCount = cell.digitToInt()
                    Cell.Block(coordinate, aroundMineCount)
                }
            }
        }
    )

    override fun generate(area: Area, mineCount: MineCount): List<Cell> = cells
}

fun Board(vararg board: String): Board {
    return Board(Area(1, 1), MineCount(1), TestCellsGenerator(*board))
}

fun Board(cells: List<Cell>): Board {
    return Board(Area(1, 1), MineCount(1), TestCellsGenerator(cells))
}
