import domain.MineSweeperGame
import view.MineSweeperGameView
import view.MineSweeperInputView

fun main() {
    val width = MineSweeperInputView.getWidth()
    val height = MineSweeperInputView.getHeight()
    val numberOfMine = MineSweeperInputView.getNumberOfMine()
    val game = MineSweeperGame.makeGame(width, height, numberOfMine)
    MineSweeperGameView.drawMineSweeperMap(game)
}
