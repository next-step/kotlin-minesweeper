package controller

import domain.Board
import domain.Coordinate
import domain.Height
import domain.MineCnt
import domain.Width
import dto.BoardDto
import view.InputView
import view.OutputView

class MineSweeperController {
    fun execute() {
        val (height, width, mineCnt) = InputView.readMineSweeperInput()
        val board = Board(
            height = Height(height),
            width = Width(width),
            mineCnt = MineCnt(mineCnt)
        )

        OutputView.printStartGame()
        playGame(board)
    }

    private tailrec fun playGame(board: Board) {
        if (board.isGameOver()) {
            OutputView.printWinGame()
            return
        }

        val (height, width) = InputView.readOpenCoordinate(board.height.value, board.width.value)
        val coordinate = Coordinate(height, width, board.height, board.width)
        if (board.isMine(coordinate)) {
            OutputView.printLoseGame()
            return
        }

        board.open(coordinate)
        OutputView.printBoard(BoardDto(board))
        playGame(board)
    }
}
