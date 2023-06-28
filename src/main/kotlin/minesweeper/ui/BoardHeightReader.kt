package minesweeper.ui

object BoardHeightReader {
    fun read(): Int {
        println("높이를 입력하세요.")
        val height = readln().toInt()
        println()
        return height
    }
}
