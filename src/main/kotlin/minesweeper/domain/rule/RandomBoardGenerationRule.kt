package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell

class RandomBoardGenerationRule : BoardGenerationRule {
    override fun generate(metadata: BoardMetadata): List<List<Cell>> {
        val emptyBoard: List<List<Cell>> = List(metadata.height) { List(metadata.width) { Cell.EMPTY } }
        val flattenedCoordinates = (0 until metadata.height).flatMap { row ->
            (0 until metadata.width).map { col ->
                row to col
            }
        }
        val randomCoordinates = flattenedCoordinates.shuffled().take(metadata.numOfMine)

        val resultBoard = emptyBoard.toMutableList().map { it.toMutableList() }
        for ((row, col) in randomCoordinates) {
            resultBoard[row][col] = Cell.MINE
        }

        return resultBoard
    }
}
