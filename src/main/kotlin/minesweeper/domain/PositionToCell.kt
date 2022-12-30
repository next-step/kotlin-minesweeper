package minesweeper.domain

data class PositionToCell(val pair: Pair<Position, Cell>) {

    constructor(position: Position, minePositions: List<Position>) :
            this(position to createCell(minePositions, position))

    companion object {
        private fun createCell(
            minePositions: List<Position>,
            position: Position,
        ) = if (minePositions.contains(position)) MineCell(position)
        else CleanCell(position, minePositions)
    }
}
