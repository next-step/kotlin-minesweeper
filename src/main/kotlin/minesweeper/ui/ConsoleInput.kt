package minesweeper.ui

object ConsoleInput {
    fun inputRowLength(): Int {
        println("높이를 입력하세요.")
        val rowLength = readln().toInt()
        println()
        return rowLength
    }

    fun inputColumnLength(): Int {
        println("너비를 입력하세요.")
        val columnLength = readln().toInt()
        println()
        return columnLength
    }

    fun inputCountOfLandmine(): Int {
        println("지뢰는 몇 개인가요?")
        val countOfLandmine = readln().toInt()
        println()
        return countOfLandmine
    }
}
