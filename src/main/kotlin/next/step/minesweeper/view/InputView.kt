package next.step.minesweeper.view

import next.step.blackjack.view.OutputView
import next.step.minesweeper.domain.board.BoardHeight
import next.step.minesweeper.domain.board.BoardWidth
import next.step.minesweeper.domain.mine.MineCount


object InputView {

    private const val ENTER_HEIGHT = "높이를 입력하세요."
    private const val ENTER_WIDTH = "너비를 입력하세요."
    private const val ENTER_MINE_CNT = "지뢰는 몇 개인가요?"


    fun readHeight(): BoardHeight = read(ENTER_HEIGHT) { BoardHeight.of(it.toInt()) }

    private fun <T> read(enterMsg: String, constructor: (String) -> T): T {
        return runCatching {
            println()
            println(enterMsg)
            constructor(readln())
        }.onSuccess {
            return it
        }.onFailure { e ->
            OutputView.showError(e.message)
            return read(enterMsg, constructor)
        }.getOrThrow()
    }

    fun readWidth(): BoardWidth = read(ENTER_WIDTH) { BoardWidth.of(it.toInt()) }

    fun readMineCnt(): MineCount = read(ENTER_MINE_CNT) { MineCount.of(it.toInt()) }

}
