import domain.game.RealMineSweeperGameFactory
import domain.map.RealMineMapFactory
import domain.mine.RealMineCoordinatesCreator
import view.MineSweeperController
import view.MineSweeperInputView
import view.MineSweeperResultView

fun main() {
    MineSweeperController(
        inputView = MineSweeperInputView(),
        resultView = MineSweeperResultView(),
        mineSweeperGameFactory = RealMineSweeperGameFactory(
            mineMapFactory = RealMineMapFactory(
                mineCoordinatesCreator = RealMineCoordinatesCreator(),
            ),
        ),
    ).start()
}
