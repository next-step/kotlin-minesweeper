package minesweeper.view

interface ViewStrategy {

    fun printAny(any: Any)

    fun printWin()

    fun printLose()

    fun printCellMessage()

    fun getLine(): String
}
