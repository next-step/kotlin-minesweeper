package minesweeper.model.map.coordinate

interface Area {
    val columnCount: Int
    val rowCount: Int
    val cellCount: Int
        get() = columnCount * rowCount
}
