package tdd.minesweeper.view

object InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun getMineCount(): Int {
        println("지뢰 개수를 입력하세요.")
        return readln().toInt()
    }

    fun getCoordinate(): Pair<Int, Int> {
        println("좌표를 입력하세요.")
        return readln().split(",")
            .let { it[0].toInt() to it[1].toInt() }
    }
}
