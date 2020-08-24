import minesweeper.domain.MinesweeperGame
import minesweeper.domain.MinesweeperGameResult
import minesweeper.domain.PlayState
import minesweeper.domain.PositionCheckResult
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
        // else를 쓰지 않는 이유는 sealed class에 새로운 sub class가 생기는 경우
        // when expression에서 컴파일 에러가 나도록 만들기 위함입니다.
        is MinesweeperGameResult.InvalidHeight,
        is MinesweeperGameResult.InvalidWidth,
        is MinesweeperGameResult.InvalidMineCount ->
            ResultView.showErrorMessage(result)
    }.exhaustive
}

fun playMinesweeperGame(minesweeperGame: MinesweeperGame) {
    try {
        while (minesweeperGame.playState.ordinal <= PlayState.PLAYING.ordinal) {
            ResultView.showMinesweeperBoard(minesweeperGame)
            val positionCheckResult: PositionCheckResult = minesweeperGame.openCell(InputView.getPosition())
            ResultView.showErrorMessage(positionCheckResult)
        }
        ResultView.showMinesweeperBoard(minesweeperGame)
    } catch (e: Exception) {
        ResultView.showErrorMessage(e.message.toString())
    }
}
