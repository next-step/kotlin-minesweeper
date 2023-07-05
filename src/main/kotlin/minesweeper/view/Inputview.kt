package minesweeper.view

object Inputview {
    fun askHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun askWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun askMineNumber(): Int {
        println("지뢰는 몇개인가요?")
        return readln().toInt()
    }

    fun askOpenPosition(): List<Int> {
        print("open: ")
        return readln().split(", ").map { it.toInt() }.toList()
    }
}
