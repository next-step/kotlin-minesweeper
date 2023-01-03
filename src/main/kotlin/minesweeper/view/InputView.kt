package minesweeper.view

import java.lang.IllegalArgumentException

object InputView {
    fun askHeight(): Int {
        println("높이를 입력하세요.")
        val height = readln().toIntOrNull()
        requireNotNull(height) { "높이는 숫자를 입력해야 합니다." }
        require(height > 0) { "높이는 0보다 큰 숫자를 입력해야 합니다." }
        return height
    }

    fun askWidth(): Int {
        println("너비를 입력하세요.")
        val width = readln().toIntOrNull()
        requireNotNull(width) { "너비는 숫자를 입력해야 합니다." }
        require(width > 0) { "너비는 0보다 큰 숫자를 입력해야 합니다." }
        return width

    }

    fun askMineCount(maximumCount: Int): Int {
        println("지뢰는 몇 개인가요?")
        val mineCount = readln().toIntOrNull()
        requireNotNull(mineCount) { "지뢰의 개수는 숫자를 입력해야 합니다." }
        require(mineCount <= maximumCount) { "지뢰의 개수는 높이 x 너비 (${maximumCount})이하로 입력해야 합니다." }
        return mineCount
    }

    fun askOpenPosition(height: Int, width: Int): Pair<Int, Int> {
        print("open: ")
        val position = readln().split(",")
            .map { it.trim() }
            .map { it.toIntOrNull() ?: throw IllegalArgumentException("오픈할 위치는 1, 1 형식의 숫자로 입력해 주어야 합니다.") }
        require(position.size == 2) { "오픈할 위치는 1, 1 형식의 숫자로 입력해 주어야 합니다." }
        require(position[0] in 0 until height) { "앞의 숫자는 0보다 크거나 같고 ${height}보다 작아야 합니다." }
        require(position[1] in 0 until width) { "앞의 숫자는 0보다 크거나 같고 ${width}보다 작아야 합니다." }

        return Pair(position[0], position[1])
    }
}
