package minesweeper.controller

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.domain.position.Position
import minesweeper.dto.MineSweeperDTO
import minesweeper.utils.StringUtils
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController {

    fun start(inputView: InputView, resultView: ResultView) {
        val boardSize = BoardSize.of(inputView.inputWidth(), inputView.inputHeight())
        val mineCount = inputView.inputMine()
        val realBoard = Board.of(boardSize, mineCount)
        resultView.gameStartView()
        while (!realBoard.isAllOpenedExcludeMine()) {
            val (x, y) = StringUtils.split(inputView.inputOpenPosition())
            realBoard.open(Position.of(x, y))
            if (realBoard.isOpenedMine()) {
                resultView.loseResultView()
                break
            }
            resultView.boardView(MineSweeperDTO.of(realBoard.cells))
        }
        if (realBoard.isAllOpenedExcludeMine()) {
            resultView.winResultView()
        }
    }
}
