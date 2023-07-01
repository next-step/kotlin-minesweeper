package next.step.minesweeper.controller

import next.step.blackjack.view.OutputView
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.generator.RandomMineGenerator
import next.step.minesweeper.view.InputView

fun main() {
    runCatching {
        val board = Board.mineFree(InputView.readHeight(), InputView.readWidth())
        plantRandomMines(board)
        OutputView.showBoardPoints(board.points())
    }.onFailure { e ->
        OutputView.showError(e.message)
        main()
    }
}

private fun plantRandomMines(board: Board) {
    runCatching {
        board.plantMines(RandomMineGenerator.generate(board, InputView.readMineCnt()))
    }.onFailure { e ->
        OutputView.showError(e.message)
        plantRandomMines(board)
    }
}
