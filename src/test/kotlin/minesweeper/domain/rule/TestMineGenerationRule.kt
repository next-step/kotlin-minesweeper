package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.Coordinate

class TestMineGenerationRule(private val mineCoordinates: List<Pair<Int, Int>>) : MineGenerationRule {

    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val resultBoard: MutableMap<Coordinate, Cell> =
            (0 until metadata.width).flatMap { row ->
                (0 until metadata.height).map { col ->
                    Coordinate(row, col) to Cell(CellType.EMPTY)
                }
            }.toMap().toMutableMap()

        for (coordinate in mineCoordinates) {
            resultBoard[Coordinate(coordinate.first, coordinate.second)] = Cell(CellType.MINE)
        }

        return resultBoard
    }
}
