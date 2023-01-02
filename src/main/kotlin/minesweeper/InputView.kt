package minesweeper

object InputView {
    fun height(): Int {
        println("높이를 입력해하세요.")
        return readLine()!!.toInt()
    }

    fun width(): Int {
        println()
        println("너비를 입력해주세요.")
        return readLine()!!.toInt()
    }

    fun mineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")
        return readLine()!!.toInt()
    }

    fun point(): Pair<Int, Int> {
        print("open: ")
        return readLine()!!.split(",")
            .map { it.trim().toInt() }
            .let { it[0] to it[1] }
    }
}
