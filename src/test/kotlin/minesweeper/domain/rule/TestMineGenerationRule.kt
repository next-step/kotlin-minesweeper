package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.Coordinate

class TestMineGenerationRule(private val mineCoordinates: List<Coordinate>) : MineGenerationRule {

    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val coordinates = (0 until metadata.width).flatMap { row ->
            (0 until metadata.height).map { col ->
                Coordinate(row, col)
            }
        }
        return coordinates.associateWith {
            if (mineCoordinates.contains(it)) {
                return@associateWith Cell(CellType.MINE)
            }

            Cell(CellType.EMPTY)
        }
    }
}
