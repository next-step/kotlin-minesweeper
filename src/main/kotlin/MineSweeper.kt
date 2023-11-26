import model.GameMap
import view.InputView
import view.OutputView

class MineSweeper {

    fun start() {
        val map = GameMap(
            InputView.inputHeight(),
            InputView.inputWidth(),
            InputView.inputMineCount()
        )

        OutputView.renderMap(map)
    }
}
