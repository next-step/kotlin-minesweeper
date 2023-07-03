package minesweeper.ui

object BoardWidthReader {
    fun read(): Int {
        println("너비를 입력하세요.")
        val width = readln().toInt()
        println()
        return width
    }
}
