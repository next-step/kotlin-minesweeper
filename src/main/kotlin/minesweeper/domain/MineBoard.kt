package minesweeper.domain

class MineBoard(
    val height: Int,
    val width: Int,
    val totalMineCount: Int
) {
    val buttons: Buttons = Buttons.of(height, width, totalMineCount)

    init {
        require(height > 0) {
            "height should be greater than 0 [$height]"
        }

        require(width > 0) {
            "width should be greater than 0 [$width]"
        }

        require(totalMineCount in (0..height * width)) {
            "totalMineCount [$totalMineCount] should be in range ${(0..height * width)}"
        }
    }
}
