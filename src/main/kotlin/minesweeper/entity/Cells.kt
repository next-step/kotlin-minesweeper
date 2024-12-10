package minesweeper.entity

@JvmInline
value class Cells(
    val cells: List<Cell>,
) {
    fun findCell(coordinate: Coordinate): Cell {
        return cells.find { it.matches(coordinate) }
            ?: throw IllegalArgumentException("셀을 찾을 수 없습니다: $coordinate")
    }
}
