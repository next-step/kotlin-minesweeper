package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell

class TestBoardGenerationRule(private val mineCoordinates: List<Pair<Int, Int>>) : BoardGenerationRule {

    override fun generate(metadata: BoardMetadata): List<List<Cell>> {
        val emptyBoard: List<List<Cell>> = List(metadata.height) { List(metadata.width) { Cell.EMPTY } }
        val resultBoard = emptyBoard.toMutableList().map { it.toMutableList() }
        for ((row, col) in mineCoordinates) {
            resultBoard[row][col] = Cell.MINE
        }

        return resultBoard
    }
}
