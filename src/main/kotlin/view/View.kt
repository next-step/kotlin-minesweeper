package view

import vo.BoardVO

object InputView {
    private const val COMMA_SEPARATOR = ","

    fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("높이를 입력해주세요.")
    }

    fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("너비를 입력해주세요.")
    }

    fun readMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("지뢰 개수를 입력해주세요.")
    }

    fun readCoordinateToOpen(): Pair<Int, Int> {
        print("open: ")
        val coordinateString = readlnOrNull() ?: throw IllegalArgumentException("열고자 하는 칸의 좌표를 입력해주세요. 예. 1, 1")
        val coordinate = coordinateString.split(COMMA_SEPARATOR).map { it.toInt() }
        return coordinate[0] to coordinate[1]
    }
}

object ResultView {
    private const val NEW_LINE = "\n"
    private const val SPACE = " "

    fun printStartGame() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(boardVO: BoardVO) {
        val boardDisplay = boardVO.rows.joinToString(NEW_LINE) { row ->
            row.cells.joinToString(SPACE) { it }
        }
        println(boardDisplay)
    }

    fun printLoseGame() {
        println("Lose Game.")
    }

    fun printWinGame() {
        println("Win Game.")
    }
}
