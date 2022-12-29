package minesweeper.domain

private const val CLEAN_SIGN = "C"

data class CleanCell(
    override val position: Position,
    var nearMineCount: Int = 0,
) : Cell(position, CLEAN_SIGN) {
    constructor(position: Position, minePositions: List<Position>) : this(position, position.getNearPositions()
        .count { minePositions.contains(it) })

}

