package minesweeper.domain

@JvmInline
value class Board private constructor(val cells: Cells) {

    companion object {

        fun from(cells: Cells): Board {
            return Board(cells)
        }

        fun of(width: Width, height: Height): Board {
            val positions = Positions.of(width, height)
            val cells = Cells.of(positions)
            return from(cells)
        }
    }
}
