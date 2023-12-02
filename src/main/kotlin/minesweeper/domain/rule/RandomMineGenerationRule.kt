package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.Coordinate

class RandomMineGenerationRule : MineGenerationRule {
    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val resultBoard: MutableMap<Coordinate, Cell> =
            (0 until metadata.width).flatMap { row ->
                (0 until metadata.height).map { col ->
                    Coordinate(row, col) to Cell(CellType.EMPTY)
                }
            }.toMap().toMutableMap()

        val coordinates = resultBoard.keys.toList()
        val mineCoordinates = coordinates.shuffled().take(metadata.numOfMine)
        for (coordinate in mineCoordinates) {
            resultBoard[coordinate] = Cell(CellType.MINE)
        }

        return resultBoard
    }
}
