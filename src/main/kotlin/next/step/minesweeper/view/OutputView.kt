package next.step.blackjack.view

import next.step.minesweeper.domain.board.Board

object OutputView {

    private const val UNKNOWN_ERR_MSG = "알 수 없는 에러가 발생했습니다."
    private const val BOARD_TITLE = "지뢰찾기 게임 시작"

    fun showBoard(board: Board) {
        println()
        println(BOARD_TITLE)
        board.descs().forEach { printBoardRow(it) }
    }

    private fun printBoardRow(it: List<String>) {
        println(it.joinToString(" "))
    }

    fun showError(msg: String?) {
        println(msg ?: UNKNOWN_ERR_MSG)
        println()
    }
}
