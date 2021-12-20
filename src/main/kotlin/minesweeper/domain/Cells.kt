package minesweeper.domain

@JvmInline
value class Cells private constructor(private val _cells: List<Cell>) {

    val cells: List<Cell>
        get() = _cells.toList()

    companion object {

        fun of(positions: List<Position>, minePositions: List<Position>): Cells {
            return from(
                positions.map {
                    if (minePositions.contains(it)) {
                        Cell.MineCell(it)
                    } else {
                        Cell.SafetyCell(it, it.countAroundPositionsContainOthers(minePositions))
                    }
                }
            )
        }

        fun from(cellsList: List<Cell>): Cells {
            return Cells(cellsList.sortedBy { it.position })
        }
    }
}
