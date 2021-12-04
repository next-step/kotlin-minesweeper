package minesweeper.controller

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.domain.cell.CellsState
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
        gameInProgress(realBoard, resultView)
    }

    private tailrec fun gameInProgress(board: Board, resultView: ResultView) {
        val (x, y) = StringUtils.split(InputView.inputOpenPosition())
        when (board.open(Position.of(x, y))) {
            CellsState.EXIST_MINE -> {
                resultView.boardView(MineSweeperDTO.of(board.cells))
                gameInProgress(board, resultView)
            }
            CellsState.BOMB -> resultView.loseResultView()
            CellsState.NOT_EXIST_MINE -> resultView.winResultView()
        }
    }
}
