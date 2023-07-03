package next.step.minesweeper.view

import next.step.minesweeper.controller.onFailure
import next.step.minesweeper.domain.board.BoardHeight
import next.step.minesweeper.domain.board.BoardWidth
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.utils.retryOnFailure

object InputView {

    private const val ENTER_HEIGHT = "높이를 입력하세요."
    private const val ENTER_WIDTH = "너비를 입력하세요."
    private const val ENTER_MINE_CNT = "지뢰는 몇 개인가요?"
    private const val ENTER_POSITION = "open: "

    fun readHeight(): BoardHeight = read(ENTER_HEIGHT) { BoardHeight(it.toInt()) }

    private fun <T> read(enterMsg: String, constructor: (String) -> T): T =
        retryOnFailure({
            println()
            println(enterMsg)
            constructor(readln())
        }) { onFailure(it) }

    fun readWidth(): BoardWidth = read(ENTER_WIDTH) { BoardWidth(it.toInt()) }

    fun readMineCnt(): MineCount = read(ENTER_MINE_CNT) { MineCount(it.toInt()) }

    fun readPosition(): Position = read(ENTER_POSITION) { entered ->
        val tokens = entered.split(",").map { it.trim().toInt() }
        Position(tokens[0], tokens[1])
    }
}
