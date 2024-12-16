package minesweeper.model

/**
 * @author 이상준
 */
class Cell(
    val row: Int = 0,
    val column: Int = 0,
) {
    private var isMine: Boolean = false

    fun isMine(): Boolean = isMine

    fun addMine() {
        isMine = true
    }
}
