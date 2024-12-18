package minesweeper.application

import minesweeper.domain.Game
import minesweeper.domain.PlayableBoard
import minesweeper.domain.PlayableGame

class MinesweeperService(
    private val boardGenerator: BoardGenerator,
) {
    fun newGame(command: GenerateMinesweeperCommand): Game {
        val board = boardGenerator.generate(command) as PlayableBoard
        return PlayableGame(board)
    }
}
