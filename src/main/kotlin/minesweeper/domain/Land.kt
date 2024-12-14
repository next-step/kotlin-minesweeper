package minesweeper.domain

class Land(
    val point: Point,
    val aroundMineCount: Int,
    private var isOpened: Boolean = false,
) {
    fun isOpened(): Boolean = isOpened

    fun open() {
        isOpened = true
    }
}
