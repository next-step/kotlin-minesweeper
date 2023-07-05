package mine.sweeper

fun main() {
    val gameController = GameController()
    gameController.create()
    gameController.setMines()
    gameController.start()
}
