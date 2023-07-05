package next.step.minesweeper.controller

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.board.BoardArea
import next.step.minesweeper.domain.mine.generator.RandomMineGenerator
import next.step.minesweeper.utils.retryOnFailure
import next.step.minesweeper.view.InputView
import next.step.minesweeper.view.OutputView

fun main() {
    retryOnFailure({
        val boardArea = BoardArea(InputView.readHeight(), InputView.readWidth())
        val board = retryOnFailure(
            { Board.of(boardArea, RandomMineGenerator, InputView.readMineCnt()) },
        ) { onFailure(it) }

        OutputView.showTitle()
        board.play(
            { InputView.readPosition() },
            { OutputView.showBoardPoints(it) },
            { OutputView.showWin() },
            { OutputView.showLose() },
        ) { onFailure(it) }
    }) { onFailure(it) }
}

val onFailure: (Throwable) -> Unit = { OutputView.showError(it.message) }
