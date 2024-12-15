
import domain.MineSweeperGame
import view.MineSweeperInputView
import view.MineSweeperMapView

fun main() {
    val width = MineSweeperInputView.getWidth()
    val height = MineSweeperInputView.getHeight()
    val numberOfMine = MineSweeperInputView.getNumberOfMine()
    val game = MineSweeperGame.of(width, height, numberOfMine)
    MineSweeperMapView.drawStart()
    while (!game.isWin()) {
        MineSweeperMapView.drawMineSweeperMap(game.mineSweeperMap)
        val (x, y) = MineSweeperInputView.getOpenInput()
        if (!game.open(x, y)) {
            break
        }
    }
    if (game.isWin()) {
        MineSweeperMapView.drawWinResult(game.mineSweeperMap)
    } else {
        println("Lose Game")
    }
}
