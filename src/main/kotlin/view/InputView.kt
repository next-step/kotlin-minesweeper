package view

object InputView {
    fun readHeight(): Int {
        println("높이를 입력하세요.")

        return readln().toInt()
    }

    fun readWidth(): Int {
        println()
        println("너비를 입력하세요.")

        return readln().toInt()
    }

    fun readMineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")

        return readln().toInt()
    }

    fun readOpenCoordinate(): List<Int> {
        print("open: ")
        return readln().split(", ").map { it.toInt() }
    }
}
