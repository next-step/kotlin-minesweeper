package minesweeper.domain

class Board(
    val cells: List<Cells>,
) {
    private val width: Int = cells.first().size
    private val height: Int = cells.size

    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()
    }
    private fun validateHeightIsPositive() {
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }

    private fun validateWidthIsPositive() {
        require(width > 0) { "너비는 0보다 커야 합니다." }
    }
    private fun validateSameWidth() {
        require(cells.all { it.hasSize(width) }) { "너비가 일정하지 않습니다."}
    }
}
