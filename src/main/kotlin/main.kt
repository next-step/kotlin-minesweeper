import domain.mine.RealMineCoordinatesCreator
import view.MineSweeperController
import view.MineSweeperInputView
import view.MineSweeperResultView

fun main() {
    MineSweeperController(
        inputView = MineSweeperInputView(),
        resultView = MineSweeperResultView(),
        mineCoordinatesCreator = RealMineCoordinatesCreator(),
    ).start()
}
