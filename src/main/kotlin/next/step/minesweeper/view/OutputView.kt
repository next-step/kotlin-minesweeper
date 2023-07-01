package next.step.blackjack.view

import next.step.minesweeper.domain.board.BoardPoint
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineState

object OutputView {

    private const val UNKNOWN_ERR_MSG = "알 수 없는 에러가 발생했습니다."
    private const val BOARD_TITLE = "지뢰찾기 게임 시작"

    fun showBoardPoints(boardPoints: List<List<BoardPoint>>) {
        println()
        println(BOARD_TITLE)
        boardPoints.forEach { printBoardRow(it) }
    }

    private fun printBoardRow(it: List<BoardPoint>) = println(desc(it).joinToString(" "))

    private fun desc(boardPoints: List<BoardPoint>): List<String> = boardPoints.map { desc(it) }

    private fun desc(boardPoint: BoardPoint): String = when (boardPoint.state()) {
        CoveredState -> "C"
        MineState -> "*"
    }

    fun showError(msg: String?) {
        println(msg ?: UNKNOWN_ERR_MSG)
        println()
    }
}
