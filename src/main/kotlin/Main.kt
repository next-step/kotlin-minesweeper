import controller.MinesweeperController
import enums.MineType
import factory.MinesweeperFactory
import view.InputView
import view.ResultView

fun main() {

    val minesweeperController = MinesweeperController(
        inputView = InputView,
        resultView = ResultView,
        minesweeperFactory = MinesweeperFactory(),
        mineType = MineType.BASIC
    )

    minesweeperController.start()
}