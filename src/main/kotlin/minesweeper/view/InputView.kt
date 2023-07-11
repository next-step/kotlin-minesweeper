package minesweeper.view

import minesweeper.domain.Point

object InputView {

    fun inputBoardInfo(): BoardStatus {
        return BoardStatus(
            inputHeight(), inputWidth(), inputMineCount()
        )
    }
    fun inputPoint(): Point {
        print("open: ")
        val input = readLine()?.split(",") ?: throw IllegalArgumentException("입력이 없습니다.")

        val row = input[0].trim().toInt()
        val col = input[1].trim().toInt()

        return Point(row, col)
    }

    private fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("높이는 숫자여야 합니다.")
    }

    private fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("너비는 숫자여야 합니다.")
    }

    private fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("지뢰 개수는 숫자여야 합니다.")
    }
}
