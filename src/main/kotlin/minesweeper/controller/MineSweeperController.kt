package minesweeper.controller

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.domain.dto.MineSweeperDTO
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController {

    fun start(inputView: InputView, resultView: ResultView) {
        val boardSize = BoardSize.of(inputView.inputWidth(), inputView.inputHeight())
        val mineCount = inputView.inputMine()
        val realBoard = Board.of(boardSize, mineCount)
        resultView.boardView(MineSweeperDTO.of(realBoard.cells))
    }
}
