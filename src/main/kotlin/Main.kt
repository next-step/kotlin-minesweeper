import minesweeper.domain.MinesweeperGame
import minesweeper.domain.MinesweeperGameResult
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {

    val result = MinesweeperGame.requestGame(
        InputView.getHeight(),
        InputView.getWidth(),
        InputView.getMinCount()
    )

    val minesweeperGame: MinesweeperGame
    when (result) {
        is MinesweeperGameResult.Success -> {
            minesweeperGame = MinesweeperGame.newInstance(result.height, result.width, result.mineCount)
            ResultView.showMinesweeperBoard(minesweeperGame.minesweeperBoard)
        }
        is MinesweeperGameResult.Error -> {
            println(result.message)
        }
    }
}
