import domain.MineSweeperGame
import view.MineSweeperInputView

fun main() {
    val width = MineSweeperInputView.getWidth()
    val height = MineSweeperInputView.getHeight()
    val numberOfMine = MineSweeperInputView.getNumberOfMine()

    MineSweeperGame.makeGame(width, height, numberOfMine)
}
