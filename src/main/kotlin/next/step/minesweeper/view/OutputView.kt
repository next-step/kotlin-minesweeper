package next.step.blackjack.view

import next.step.minesweeper.domain.mine.MineBoard
import next.step.minesweeper.domain.position.Position

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
        (Position.base().y until mineBoard.height()).forEach { println(row(mineBoard, it)) }
    }

    private fun row(mineBoard: MineBoard, y: Int): String =
        (Position.base().x until mineBoard.width()).joinToString(" ") { x ->
            if (mineBoard.isMineAt(Position(x, y))) MINE_DESC else PLAIN_DESC
        }
}
