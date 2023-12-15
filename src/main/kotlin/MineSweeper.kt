import gamemap.GameMapCreator
import gamemap.OpenResult
import view.GameState
import view.InputView
import view.ResultView

class MineSweeper(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    private var gameState = GameState.Initial
    fun run() {
        val gameMapCreator = initGameMapCreator()
        val gameMap = gameMapCreator.create()

        resultView.printGameStart()
        resultView.printGameMap(gameMap)
        gameState = GameState.Playing

        while (gameState == GameState.Playing) {
            val (row, col) = inputView.getCommand()
            val openResult = gameMap.open(row, col)
            resultView.printGameMap(gameMap)
            if (openResult == OpenResult.Fail) {
                gameState = GameState.Lose
            }
            if (openResult == OpenResult.Complete) {
                gameState = GameState.Win
            }
        }

        resultView.printGameOver(gameState == GameState.Win)
    }

    private fun initGameMapCreator(): GameMapCreator {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()

        return GameMapCreator(
            width = width,
            height = height,
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
