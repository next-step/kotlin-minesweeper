package next.step.minesweeper.controller

import next.step.blackjack.view.OutputView
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.board.BoardArea
import next.step.minesweeper.domain.mine.generator.RandomMineGenerator
import next.step.minesweeper.utils.retryOnFailure
import next.step.minesweeper.view.InputView

fun main() = retryOnFailure {
    val boardArea = BoardArea(InputView.readHeight(), InputView.readWidth())
    val board = Board.mineFree(boardArea)
    plantRandomMines(board)
    board.cover()
    OutputView.showTitle()
    board.play(
        { InputView.readPosition() },
        { OutputView.showError(it.message) },
        { OutputView.showBoardPoints(it) },
        { OutputView.showSuccess() },
        { OutputView.showFail() })
}

private fun plantRandomMines(board: Board) = retryOnFailure {
    board.plantMines(RandomMineGenerator.generate(board.area, InputView.readMineCnt()))
}
