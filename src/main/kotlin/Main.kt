
import domain.MineSweeperGame
import view.MineSweeperInputView
import view.MineSweeperMapView
import view.MineSweeperResultView

fun main() {
    val width = MineSweeperInputView.getWidth()
    val height = MineSweeperInputView.getHeight()
    val numberOfMine = MineSweeperInputView.getNumberOfMine()
    val game = MineSweeperGame.of(width, height, numberOfMine)
    MineSweeperMapView.drawStart()
    doGame(game)
    MineSweeperResultView.drawResult(game)
}

private fun doGame(game: MineSweeperGame) {
    while (!game.isWin()) {
        MineSweeperMapView.drawMineSweeperMap(game.mineSweeperMap)
        val (x, y) = MineSweeperInputView.getOpenInput()
        if (!game.open(x, y)) break
    }
}
