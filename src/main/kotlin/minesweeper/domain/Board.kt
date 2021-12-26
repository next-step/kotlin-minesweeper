package minesweeper.domain

@JvmInline
value class Board private constructor(private val _cells: Cells) {

    val cellList: List<Cell>
        get() = _cells.cells.toList()

    companion object {

        const val MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION = "지뢰의 숫자는 Cell의 갯수와 같거나 작아야 합니다."

        fun from(cells: Cells): Board {
            return Board(cells)
        }

        fun ofSizeAndMineCount(width: Width, height: Height, mineCount: MineCount): Board {
            require(width.value * height.value >= mineCount.count) { MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION }
            val positions = Positions.of(width, height)
            val minePositions = minePositions(positions, mineCount)

            return from(Cells.of(positions.positions, minePositions))
        }

        private fun minePositions(positions: Positions, mineCount: MineCount) = positions.shuffled().take(mineCount.count)
    }
}
