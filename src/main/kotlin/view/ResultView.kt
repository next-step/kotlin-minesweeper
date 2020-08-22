package view

import domain.Block
import domain.Board
import domain.Result

object ResultView {
    private const val START_GAME = "지뢰찾기 게임 시작"
    private const val SYMBOL_HIDDEN: String = "?"
    private const val SYMBOL_MINE = "*"
    private const val RESULT_WIN = "You Win"
    private const val RESULT_LOSE = "You Lose"
    private const val RESULT_ALREADY_OPEN = "이미 열려있는 칸입니다."
    private const val RESULT_INVALID = "올바른 좌표가 아닙니다"

    fun printStart() {
        println(START_GAME)
    }

    fun printBoard(board: Board, lineBreakInterval: Int) {
        board.map.entries.forEachIndexed { index, block ->
            print(findSymbol(block.value))
            print(if (index % lineBreakInterval == lineBreakInterval - 1) "\n" else " ")
        }
    }

    fun printResult(result: Result) {
        if (result == Result.PROGRESS) {
            return
        }
        println(
            when (result) {
                Result.WIN -> RESULT_WIN
                Result.ALREADY_OPEN -> RESULT_ALREADY_OPEN
                Result.INVALID -> RESULT_INVALID
                else -> RESULT_LOSE
            }
        )
    }

    private fun findSymbol(block: Block) = when {
        !block.isOpened -> SYMBOL_HIDDEN
        block.isMine() -> SYMBOL_MINE
        else -> block.mineCount.toString()
    }
}
