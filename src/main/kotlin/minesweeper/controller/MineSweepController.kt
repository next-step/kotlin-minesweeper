package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.BlockGenerator
import minesweeper.domain.game.GameResult
import minesweeper.domain.game.State
import minesweeper.views.InputView
import minesweeper.views.OutputView

class MineSweepController {

    fun start() {
        val height = Height(InputView.askHeight())
        val width = Width(InputView.askWidht())
        val mineCount = InputView.askMine()
        val area = Area(width, height)

        val blocks = BlockGenerator.generateBlocks(area, mineCount)
        val board = Board(area, blocks)
        var gameResult = GameResult(State.PLAY, board)

        while (!gameResult.endGame()) {
            val position = InputView.askTarget()
            gameResult = gameResult.board.scanMine(position.x, position.y)
            OutputView.printBoard(gameResult)
        }
    }
}
