package minesweeper.domain

@JvmInline
value class Board private constructor(private val _cells: Cells) {

    val cellList: List<Pair<Position, Cell>>
        get() = _cells.cells.toList()

    companion object {

        fun from(cells: Cells): Board {
            return Board(cells)
        }

        fun of(width: Width, height: Height, mineCount: MineCount): Board {
            val positions = Positions.of(width, height)
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
