package next.step.minesweeper.controller

import next.step.blackjack.view.OutputView
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.MineBoard
import next.step.minesweeper.domain.mine.generator.RandomMineGenerator
import next.step.minesweeper.view.InputView

fun main() {
    runCatching {
        val board = Board.of(InputView.readHeight(), InputView.readWidth())
        val minePoints = RandomMineGenerator.generate(board, InputView.readMineCnt())
        val mineBoard = MineBoard.of(board, minePoints)
        OutputView.showMineBoard(mineBoard)
    }.onFailure { e ->
        OutputView.showError(e.message)
        main()
    }
}
