package minesweeper.view

object MinesweeperView {

    private const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    private const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    private const val INPUT_MINE_COUNT_MESSAGE = "지뢰는 몇 개인가요?"

    fun printInputHeight() {
        println(INPUT_HEIGHT_MESSAGE)
    }

    fun printInputWidth() {
        println(INPUT_WIDTH_MESSAGE)
    }

    fun printInputMineCount() {
        println(INPUT_MINE_COUNT_MESSAGE)
    }
}
