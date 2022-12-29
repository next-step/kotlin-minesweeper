package domain

class Board(cells: List<Cell>) {
    private val _cells: MutableList<Cell> = cells.toMutableList()
    val cells: List<Cell>
        get() = _cells.toMutableList()

    fun isMineCell(coordinate: Coordinate): Boolean {
        return cells.find { it.coordinate == coordinate } is Mine
    }

    fun markMinesAroundCount(boardInfo: BoardInfo) {
        val mineDetector = MineDetector(boardInfo, this)

        cells.filterIsInstance<Blank>().forEach() {
            val minesAroundCount = mineDetector.getMinesAroundCount(it.coordinate.x.value, it.coordinate.y.value)
            it.changeMinesAroundCount(minesAroundCount)
        }
    }
}
