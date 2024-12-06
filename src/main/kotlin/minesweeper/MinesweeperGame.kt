package minesweeper

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCount = InputView.getMineCount()

    val cells = Minesweeper.setUp(height, width)

}