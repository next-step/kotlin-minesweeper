package minesweeper.application

import minesweeper.domain.Board

class MinesweeperService(
    private val boardGenerator: BoardGenerator,
) {
    fun generateBoard(command: GenerateMinesweeperCommand): Board = boardGenerator.generate(command)
}
