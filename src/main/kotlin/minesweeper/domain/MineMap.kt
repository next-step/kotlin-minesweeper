package minesweeper.domain

class MineMap(
    minePositions: Positions,
    emptyPositions: Positions
) {
    private val values: Map<Position, Cell> =
        minePositions.associateWith { Mine() } + emptyPositions.associateWith { Empty() }

    val size: Int
        get() = values.keys.size

    fun getCell(position: Position): Cell {
        return values[position] ?: throw IllegalArgumentException("해당 위치에 셀이 없습니다")
    }
}
