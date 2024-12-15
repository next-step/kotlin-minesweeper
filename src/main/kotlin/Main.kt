
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
        print("open: ")
        val split = readln().split(",")
        require(split.size == 2) { "Invalid input" }
        if (!game.open(split[0].toInt(), split[1].toInt())) {
            break
        }
    }
    if (game.isWin()) {
        println("Win Game")
        MineSweeperMapView.drawMineSweeperMap(game.mineSweeperMap)
    } else {
        println("Lose Game")
    }
}
