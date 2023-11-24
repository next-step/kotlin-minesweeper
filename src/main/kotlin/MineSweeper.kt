import model.MapGenerator
import view.InputView
import view.OutputView

class MineSweeper {

    fun start() {
        val map = MapGenerator.generateMap(
            InputView.inputHeight(),
            InputView.inputWidth(),
            InputView.inputMineCount()
        )

        OutputView.renderMap(map)
    }
}
