package minesweeper.application

import minesweeper.domain.Game
import minesweeper.domain.PlayableGame

class MinesweeperService(
    private val boardGenerator: BoardGenerator,
) {
    fun newGame(command: GenerateMinesweeperCommand): Game {
        val board = boardGenerator.generate(command)
        return PlayableGame(board)
    }
}
