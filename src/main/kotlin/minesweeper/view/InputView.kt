package minesweeper.view

import java.lang.IllegalArgumentException

object InputView {
    fun askHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("높이는 숫자를 입력해야 합니다.")
    }

    fun askWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("너비는 숫자를 입력해야 합니다.")

    }

    fun askMineCount(maximumCount: Int): Int {
        println("지뢰는 몇 개인가요?")
        val mineCount = readln().toIntOrNull()
        requireNotNull(mineCount) { "지뢰의 개수는 숫자를 입력해야 합니다." }
        require(mineCount <= maximumCount) { "지뢰의 개수는 높이 x 너비 (${maximumCount})이하로 입력해야 합니다." }
        return mineCount
    }
}
