package minesweeper.domain

@JvmInline
value class Cells private constructor(private val _cells: Map<Position, Cell>) {

    val cells: Map<Position, Cell>
        get() = _cells.toMap()

    companion object {
        fun of(positions: Positions): Cells {
            return from(
                positions.position
                    .associateWith {
                        Cell()
                    }
            )
        }

        fun from(cellsMap: Map<Position, Cell>): Cells {
            return Cells(cellsMap.toMap())
        }
    }
}
