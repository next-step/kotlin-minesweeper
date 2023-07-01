import domain.mine.RealMineCoordinatesCreator
import view.MineSweeperController
import view.MineSweeperInputView

fun main() {
    MineSweeperController(
        inputView = MineSweeperInputView(),
        mineCoordinatesCreator = RealMineCoordinatesCreator(),
    ).start()
}
