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

    when (result) {
        is MinesweeperGameResult.Success -> {
            val minesweeperGame = MinesweeperGame.of(result.height, result.width, result.mineCount)
            ResultView.showMinesweeperBoard(minesweeperGame.minesweeperBoard)
        }
        is MinesweeperGameResult.Error -> {
            ResultView.showErrorMessage(result.getMessage(result))
        }
        is MinesweeperGameResult.InvalidHeight -> {
            ResultView.showErrorMessage(result.getMessage(result))
        }
        is MinesweeperGameResult.InvalidWidth -> {
            ResultView.showErrorMessage(result.getMessage(result))
        }
        is MinesweeperGameResult.InvalidMineCount -> {
            ResultView.showErrorMessage(result.getMessage(result))
        }
    }
}
