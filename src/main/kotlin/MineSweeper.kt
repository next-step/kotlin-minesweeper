import gamemap.GameMapCreator
import gamemap.OpenCommandResult
import view.GameState
import view.InputView
import view.ResultView

class MineSweeper(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    private var gameState: GameState = GameState.Initial
    fun run() {
        val gameMapCreator = initGameMapCreator()
        val gameMap = gameMapCreator.create()

        resultView.printGameStart()
        resultView.printGameMap(gameMap)
        gameState = gameState.play()

        while (gameState.isPlaying()) {
            val (row, col) = inputView.getCommand()
            val openResult = gameMap.openCellAt(row, col)
            resultView.printGameMap(gameMap)
            gameState = openResult.getGameState()
        }

        resultView.printGameOver(gameState)
    }

    private fun OpenCommandResult.getGameState(): GameState {
        return when (this) {
            OpenCommandResult.Fail -> GameState.Lose
            OpenCommandResult.Complete -> GameState.Win
            OpenCommandResult.Success -> GameState.Playing
        }
    }

    private fun initGameMapCreator(): GameMapCreator {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()

        return GameMapCreator(
            mapSizeParams = GameMapCreator.MapSizeParams(width = width, height = height),
            mineCount = mineCount,
        )
    }
}

fun main() {
    MineSweeper(
        inputView = InputView(),
        resultView = ResultView(),
    ).run()
}
