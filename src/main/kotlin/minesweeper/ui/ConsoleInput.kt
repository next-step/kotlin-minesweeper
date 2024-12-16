package minesweeper.ui

object ConsoleInput {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        val result = readln().toInt()
        println()
        return result
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        val result = readln().toInt()
        println()
        return result
    }

    fun inputCountOfLandmines(): Int {
        println("지뢰는 몇 개인가요?")
        val result = readln().toInt()
        println()
        return result
    }
}
