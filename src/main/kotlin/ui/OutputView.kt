package ui

import domain.Board

object OutputView {

    private const val INTERVAL_BETWEEN_DOT = " "

    fun printBoard(board: Board) {
        val boardView = board.board.toMap()
        val width = boardView.keys.maxOf { it.x }
        boardView.entries
            .sortedWith(compareBy({ it.key.x }, { it.key.y }))
            .forEachIndexed { index, entry ->
                print("${MineView.valueOf(entry.value)} $INTERVAL_BETWEEN_DOT")
                if (isNewLine(index, width)) {
                    println()
                }
            }
    }

    private fun isNewLine(index: Int, width: Int) = (index + 1).rem(width) == 0

    fun printGameStartMsg() = println("지뢰찾기 게임 시작")
}
