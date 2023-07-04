package minesweeper.domain

class Board(
    val cells: List<Cells>,
) {
    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()
    }

    private fun validateHeightIsPositive() {
        require(cells.isNotEmpty()) { "높이는 0보다 커야 합니다." }
    }

    private fun validateWidthIsPositive() {
        require(cells.first().size > 0) { "너비는 0보다 커야 합니다." }
    }

    private fun validateSameWidth() {
        val width = cells.first().size
        require(cells.all { it.values.size == width }) { "너비가 일정하지 않습니다." }
    }

    companion object {
        fun of(
            height: PositiveInt,
            width: PositiveInt,
            mineCount: PositiveInt,
        ): Board {
            val normals: Cells = Cells.normal((height * width - mineCount).value)
            val mines: Cells = Cells.mine(mineCount.value)

            return Board(
                cells = (normals + mines).shuffled().chunked(width.value),
            )
        }
    }
}
