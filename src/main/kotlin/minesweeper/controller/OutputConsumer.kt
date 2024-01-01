package minesweeper.controller

import minesweeper.domain.board.MineBoard
import minesweeper.domain.game.GameResult

interface OutputConsumer {
    fun showBoard(board: MineBoard)

    fun showGameResult(result: GameResult)
}
