package minesweeper.view

interface InputView {
    fun readHeight(): Int
    fun readWidth(): Int
    fun readMineCount(): Int
}
