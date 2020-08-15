import minesweeper.domain.MinesweeperGame
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val minesweeperGame = MinesweeperGame(
        InputView.getHeight(),
        InputView.getWidth(),
        InputView.getMinCount()
    )

    ResultView.showMinesweeperBoard(minesweeperGame.minesweeperBoard)
}
