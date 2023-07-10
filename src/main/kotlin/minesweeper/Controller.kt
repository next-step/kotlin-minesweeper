package minesweeper

class Controller(config: Config) {
    private val inputView = config.inputView()
    private val outputView = config.outputView()
    private val mineGame = config.mineGame()

    fun play() {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()

        val board = mineGame.createBoard(
            height = height,
            width = width,
            mineCount = mineCount
        )

        outputView.printMineBoard(board)
    }
}

fun main() {
    val controller = Controller(Config)
    controller.play()
}


