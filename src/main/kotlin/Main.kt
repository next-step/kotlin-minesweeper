
import domain.MineSweeperGame
import view.MineSweeperInputView
import view.MineSweeperMapView

fun main() {
    val width = MineSweeperInputView.getWidth()
    val height = MineSweeperInputView.getHeight()
    val numberOfMine = MineSweeperInputView.getNumberOfMine()
    val game = MineSweeperGame.of(width, height, numberOfMine)
    MineSweeperMapView.drawMineSweeperMap(game.mineSweeperMap)
}
