package minesweeper.ui

object BoardMineCountReader {
    fun read(): Int {
        println("지뢰는 몇 개인가요?")
        val countOfMine = readln().toInt()
        println()
        return countOfMine
    }
}
