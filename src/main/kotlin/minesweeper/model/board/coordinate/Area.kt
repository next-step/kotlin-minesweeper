package minesweeper.model.board.coordinate

interface Area {
    val columnCount: Int
    val rowCount: Int
    val cellCount: Int
        get() = columnCount * rowCount
}
