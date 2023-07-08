import controller.MinesweeperController
import service.MinesweeperService
import view.InputView
import view.ResultView

fun main() {
    MinesweeperController(InputView, ResultView, MinesweeperService()).start()
}
