package view

import domain.MineBoard

object InputView {
    fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("높이를 입력해주세요.")
    }

    fun readWidth(): Int {
        println("넓이를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("넓이를 입력해주세요.")
    }

    fun readMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("지뢰 개수를 입력해주세요.")
    }
}

object ResultView {
    private const val NEW_LINE = "\n"
    private const val SPACE = " "

    fun printMineBoards(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        val board = mineBoard.joinToString(NEW_LINE) { row ->
            row.joinToString(SPACE) { it.symbol }
        }
        println(board)
    }
}
