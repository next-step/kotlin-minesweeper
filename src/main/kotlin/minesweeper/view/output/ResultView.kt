package minesweeper.view.output

object ResultView {
    fun print(isWin: Boolean) {
        println(if (isWin) "Win Game." else "Lose Game.")
    }
}
