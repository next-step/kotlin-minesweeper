package next.step.blackjack.view

import next.step.minesweeper.domain.mine.MineBoard
import next.step.minesweeper.domain.point.Point

object OutputView {

    private const val UNKNOWN_ERR_MSG = "알 수 없는 에러가 발생했습니다."
    private const val MINE_BOARD_TITLE = "지뢰찾기 게임 시작"
    private const val MINE_DESC = "*"
    private const val PLAIN_DESC = "C"

    fun showError(msg: String?) {
        println(msg ?: UNKNOWN_ERR_MSG)
        println()
    }

    fun showMineBoard(mineBoard: MineBoard) {
        println()
        println(MINE_BOARD_TITLE)
        (mineBoard.zero().y until mineBoard.height()).forEach { println(row(mineBoard, it)) }
    }

    private fun row(mineBoard: MineBoard, y: Int): String =
        (mineBoard.zero().x until mineBoard.width()).joinToString(" ") { x ->
            if (mineBoard.isMineAt(Point.of(x, y))) MINE_DESC else PLAIN_DESC
        }
}
