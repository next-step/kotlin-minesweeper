package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate

class RandomMineGenerationRule : MineGenerationRule {
    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val resultBoard: MutableMap<Coordinate, Cell> =
            (0 until metadata.width).flatMap { x ->
                (0 until metadata.height).map { y ->
                    Coordinate(x, y) to Cell.EMPTY
                }
            }.toMap().toMutableMap()

        val coordinates = resultBoard.keys.toList()
        val mineCoordinates = coordinates.shuffled().take(metadata.numOfMine)
        for (coordinate in mineCoordinates) {
            resultBoard[coordinate] = Cell.MINE
        }

        return resultBoard
    }
}
