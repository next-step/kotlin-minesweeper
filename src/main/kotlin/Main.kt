import minesweeper.domain.MinesweeperGame
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    try {
        val minesweeperGame = MinesweeperGame(
            InputView.getHeight(),
            InputView.getWidth(),
            InputView.getMinCount()
        )
        ResultView.showMinesweeperBoard(minesweeperGame.minesweeperBoard)
    } catch (e: Exception) {
        println(e.message)
    }
}
