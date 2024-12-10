package minesweeper.app

fun main() {
    val minesweeper = Minesweeper()
    val mineField = minesweeper.setUp()
    minesweeper.gameStart(mineField)
}
