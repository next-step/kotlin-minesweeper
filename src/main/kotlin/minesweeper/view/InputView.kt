package minesweeper.view

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        val height = readln().toInt()
        check(height > 0) { "높이를 양수로 입력해 주세요" }
        return height
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        val width = readln().toInt()
        check(width > 0) { "너비를 양수로 입력해 주세요" }
        return width
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        val mineCount = readln().toInt()
        check(mineCount > 0) { "지뢰는 한개 이상 입력해주세요" }
        return mineCount
    }
}
