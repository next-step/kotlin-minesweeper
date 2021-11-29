package minesweeper.controller

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.dto.MineSweeperDTO
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController {

    fun start(inputView: InputView, resultView: ResultView) {
        val boardSize = BoardSize.of(inputView.inputWidth(), inputView.inputHeight())
        val realBoard = Board.of(boardSize)
        val mineCount = inputView.inputMine()
        realBoard.shuffleRandomMines(mineCount)
        resultView.boardView(MineSweeperDTO.of(realBoard.cells))
    }
}
