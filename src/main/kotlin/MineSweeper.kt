import gamemap.GameMapCreator
import view.InputView
import view.ResultView

class MineSweeper(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun run() {
        val gameMapCreator = initGameMapCreator()
        val gameMap = gameMapCreator.create()

        resultView.printGameStart()
        resultView.printGameMap(gameMap)
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
