package minesweeper.model

class Board(val cells: Cells) {

    val width: Width = Width.valueOf(cells.maxColumnOrNull()?.value)

    val height: Height = Height.valueOf(cells.maxRowOrNull()?.value)

    val mineCount: MineCount = cells.mineCount()

    fun mine(position: Position): Board = Board(cells.mine(position))

    companion object {
        val EMPTY = Board(Cells.EMPTY)

        fun create(width: Width, height: Height): Board {
            if (width == Width.ZERO || height == Height.ZERO) {
                return EMPTY
            }
            val positions = Position.list(width, height)
            val cells: List<Cell> = positions.map { position -> Cell.Blank(position) }
            return Board(Cells(cells))
        }

        fun shuffled(
            width: Width,
            height: Height,
            mineCount: MineCount
        ): Board {
            val size = width.value * height.value
            if (size == 0) return EMPTY
            require(size >= mineCount.value) { "지뢰의 개수는 보드 크기보다 많을 수 없음" }

            val positions = Position.list(width, height).shuffled()
            val cells = positions.mapIndexed { index, position ->
                if (index < mineCount.value) {
                    Cell.Mine(position)
                } else {
                    Cell.Blank(position)
                }
            }
            return Board(Cells(cells))
        }
    }
}
