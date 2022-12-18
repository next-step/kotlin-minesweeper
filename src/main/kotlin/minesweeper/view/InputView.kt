package minesweeper.view

object InputView {
    fun askHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun askWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()

    }

    fun askMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
