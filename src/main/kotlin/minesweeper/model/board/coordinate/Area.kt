package minesweeper.model.board.coordinate

interface Area : Iterable<Position> {

    val columnCount: PositiveInt
    val rowCount: PositiveInt
    val cellCount: Int
    val rowRange: IntRange
    val columnRange: IntRange
    fun surroundPositionsOf(position: Position): List<Position>
}
