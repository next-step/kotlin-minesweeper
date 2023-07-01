package next.step.minesweeper.controller

import next.step.blackjack.view.OutputView
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.generator.RandomMineGenerator
import next.step.minesweeper.utils.retryOnFailure
import next.step.minesweeper.view.InputView

fun main() = retryOnFailure {
    val board = Board.mineFree(InputView.readHeight(), InputView.readWidth())
    plantRandomMines(board)
    OutputView.showBoardPoints(board.points())
}

private fun plantRandomMines(board: Board) = retryOnFailure {
    board.plantMines(RandomMineGenerator.generate(board, InputView.readMineCnt()))
}
