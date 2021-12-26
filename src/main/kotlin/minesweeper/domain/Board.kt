package minesweeper.domain

@JvmInline
value class Board private constructor(private val _cells: Cells) {

    val cellList: List<Pair<Position, Cell>>
        get() = _cells.cells.toList()

    companion object {

        const val MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION = "지뢰의 숫자는 Cell의 갯수와 같거나 작아야 합니다."

        fun from(cells: Cells): Board {
            return Board(cells)
        }

        fun of(width: Width, height: Height, mineCount: MineCount): Board {
            val positions = Positions.of(width, height)
            require(width.value * height.value >= mineCount.count) { MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION }
            val cells = positions
                .shuffled()
                .mapIndexed { index, position ->
                    if (index < mineCount.count) {
                        position to Cell.MineCell
                    } else {
                        position to Cell.SafetyCell
                    }
                }
                .toMap()

            return from(Cells.from(cells))
        }
    }
}
