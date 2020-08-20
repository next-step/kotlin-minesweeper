import minesweeper.domain.MinesweeperGame
import minesweeper.domain.MinesweeperGameResult
import minesweeper.domain.PlayState
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
            while (minesweeperGame.playState == PlayState.PLAYING) {
                ResultView.showMinesweeperBoard(minesweeperGame.minesweeperBoard)
                minesweeperGame.openCell(InputView.getPosition())
            }
            ResultView.showResult(minesweeperGame)
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
