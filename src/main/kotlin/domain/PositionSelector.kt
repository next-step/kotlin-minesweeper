package domain

abstract class PositionSelector(private val height: Int, private val width: Int) {
    abstract fun selectMinePositions(mineNumber: MineNumber, excludedPosition: Position): List<Position>

    fun adjacentPositions(position: Position): List<Position> {
        val (rowIndex, columnIndex) = position.pair
        val adjacentRows = (Integer.max(rowIndex - 1, 1)..Integer.min(rowIndex + 1, height))
        val adjacentColumns = (Integer.max(columnIndex - 1, 1)..Integer.min(columnIndex + 1, width))
        return adjacentRows.map { rowIndex -> adjacentColumns.map { columnIndex -> Position(rowIndex, columnIndex) } }
            .flatten()
    }

    fun allPositions(rowRange: IntRange = (1..height), columnRange: IntRange = (1..width)): List<Position> =
        rowRange.map { rowIndex -> columnRange.map { columnIndex -> Position(rowIndex, columnIndex) } }.flatten()
}
