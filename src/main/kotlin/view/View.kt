package view

import domain.MineBoard

object InputView {
    fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("높이를 입력해주세요.")
    }

    fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("너비를 입력해주세요.")
    }

    fun readMineCount(totalCellCount: Int): Int {
        println("지뢰는 몇 개인가요?")
        val mineCount = readlnOrNull()?.toInt() ?: throw IllegalArgumentException("지뢰 개수를 입력해주세요.")
        require(mineCount <= totalCellCount) { "보드 전체 칸 수($totalCellCount)보다 지뢰가 많을 수 없습니다." }
        return mineCount
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
