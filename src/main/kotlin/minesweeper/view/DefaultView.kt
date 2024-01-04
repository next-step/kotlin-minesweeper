package minesweeper.view

class DefaultView: ViewStrategy {

    override fun printAny(any: Any) {
        Output.printAny(any)
    }

    override fun printWin() {
        Output.printWinGame()
    }

    override fun printLose() {
        Output.printLoseGame()
    }

    override fun printCellMessage() {
        Output.printCellMessage()
    }

    override fun getLine(): String = Input.getLine()
}
