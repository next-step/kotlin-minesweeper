package minesweeper.model

class Board private constructor(val cells: Cells) {

    val width: Width = cells.maxColumn()?.value?.let { Width(it) } ?: Width.ZERO
    val height: Height = cells.maxRow()?.value?.let { Height(it) } ?: Height.ZERO
    val mineCount: MineCount = cells.mineCount()

    companion object {
        val EMPTY = Board(Cells.EMPTY)

        fun create(
            width: Width,
            height: Height,
            mineCount: MineCount = MineCount.ZERO
        ): Board {
            if (width == Width.ZERO || height == Height.ZERO) {
                return EMPTY
            }
            val size = width.value * height.value
            require(size >= mineCount.count) { "지뢰의 개수는 보드 크기보다 많을 수 없음" }
            val positions = Position.list(width, height).shuffled()

            val cells = positions.mapIndexed { index, position ->
                if (index < mineCount.count) {
                    Cell.Mine(position)
                } else {
                    Cell.Blank(position)
                }
            }
            return Board(Cells(cells))
        }
    }
}
