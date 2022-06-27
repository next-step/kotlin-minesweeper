package minesweeper

import minesweeper.domain.Area
import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.CellsGenerator
import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates
import minesweeper.domain.DefaultCellsGenerator
import minesweeper.domain.MineCount
import minesweeper.domain.MineSpawner

fun OpenedCell(coordinate: Coordinate, aroundMineCount: Int = 0) =
    Cell.Block(coordinate, aroundMineCount).apply { open() }

private class TestCellsGenerator(private val cells: List<Cell>) : CellsGenerator {
    override fun generate(area: Area, mineCount: MineCount): Cells = Cells(cells)
}

private class StaticMineSpawner(private val mines: List<Coordinate>) : MineSpawner {
    override fun spawn(area: Area, count: MineCount): Coordinates = Coordinates(mines.toSet())
}

fun SampleBoard(area: Area, mineCount: MineCount, mines: List<Coordinate>): Board {
    return Board(area, mineCount, DefaultCellsGenerator(StaticMineSpawner(mines)))
}

fun SampleBoard(cells: List<Cell>): Board {
    return Board(Area(1, 1), MineCount(1), TestCellsGenerator(cells))
}
