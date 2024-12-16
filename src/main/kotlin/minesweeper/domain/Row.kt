package minesweeper.domain

data class Row(private val cells: List<Cell>) {
    fun cells(): List<Cell> = cells.toList()

    fun find(location: Location): Cell =
        cells
            .firstOrNull { it.location() == location }
            ?: throw IllegalArgumentException("해당 위치의 Cell 을 찾을 수 없습니다: location=$location")
}
