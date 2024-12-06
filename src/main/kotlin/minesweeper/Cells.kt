package minesweeper

data class Cells(val cells: List<Cell>) {
    init {
        require(cells.isNotEmpty()) { "셀은 1개 이상 이어야 합니다" }
    }

    companion object {
        private const val START_RANGE = 1

        fun generate(height: Int, width: Int): Cells {
            val cells = (START_RANGE..height).flatMap { h ->
                (START_RANGE..width).map { w ->
                    Cell(Height(h), Width(w))
                }
            }
            return Cells(cells)
        }
    }
}