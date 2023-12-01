package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.Coordinate

class TestMineGenerationRule(private val mineCoordinates: List<Pair<Int, Int>>) : MineGenerationRule {

    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val resultBoard: MutableMap<Coordinate, Cell> =
            (0 until metadata.width).flatMap { x ->
                (0 until metadata.height).map { y ->
                    Coordinate(x, y) to Cell(CellType.EMPTY, 0)
                }
            }.toMap().toMutableMap()

        for (coordinate in mineCoordinates) {
            resultBoard[Coordinate(coordinate.first, coordinate.second)] = Cell(CellType.MINE, 0)
        }

        return resultBoard
    }
}
