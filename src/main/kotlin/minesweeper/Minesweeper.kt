package minesweeper

data class Minesweeper(
    val height: Height,
    val width: Width,
    val cells: Cells,
) {
    private val heightRange: IntRange = 1..height.value
    private val widthRange: IntRange = 1..width.value

    fun chooseMineCells(mine: Int): List<Cell> {
        return List(mine) {
            Cell(
                Height(heightRange.random()),
                Width(widthRange.random()),
            )
        }
    }

    companion object {
        private const val START_RANGE = 1

        fun setUp(
            height: Int,
            width: Int,
        ): Minesweeper {
            return Minesweeper(
                Height(height),
                Width(width),
                Cells.generate(START_RANGE..height, START_RANGE..width),
            )
        }
    }
}
