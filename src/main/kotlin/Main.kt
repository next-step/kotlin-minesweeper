import minesweeper.domain.MinesweeperGame
import minesweeper.domain.MinesweeperGameResult
import minesweeper.domain.PlayState
import minesweeper.domain.exhaustive
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
            playMinesweeperGame(minesweeperGame)
        }
        is MinesweeperGameResult.Error -> {
            ResultView.showErrorMessage(result)
        }
        is MinesweeperGameResult.InvalidHeight -> {
            ResultView.showErrorMessage(result)
        }
        is MinesweeperGameResult.InvalidWidth -> {
            ResultView.showErrorMessage(result)
        }
        is MinesweeperGameResult.InvalidMineCount -> {
            ResultView.showErrorMessage(result)
        }
    }.exhaustive
}

fun playMinesweeperGame(minesweeperGame: MinesweeperGame) {
    try {
        while (minesweeperGame.playState.ordinal <= PlayState.PLAYING.ordinal) {
            ResultView.showMinesweeperBoard(minesweeperGame)
            minesweeperGame.openCell(InputView.getPosition())
        }
        ResultView.showMinesweeperBoard(minesweeperGame)
    } catch (e: Exception) {
        ResultView.showErrorMessage(e.message.toString())
    }
}
