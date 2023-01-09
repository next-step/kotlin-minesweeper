package domain

class Board(val cells: List<Cell>) {

    fun isMineCell(coordinate: Coordinate): Boolean {
        return cells.find { it.coordinate == coordinate } is Mine
    }

    fun isOpenAllBlank(): Boolean {
        val blanks = cells.filterIsInstance<Blank>()
        val openBlanks = blanks.filter { it.status == Status.OPEN }
        return blanks.size == openBlanks.size
    }

    fun findOrNull(coordinate: Coordinate): Cell? {
        return cells.find { it.coordinate == coordinate }
    }

    fun markMinesAroundCount(boardInfo: BoardInfo) {
        val mineDetector = MineDetector(boardInfo, this)

        cells.filterIsInstance<Blank>().forEach {
            val minesAroundCount = mineDetector.getMinesAroundCount(it.coordinate)
            it.changeMinesAroundCount(minesAroundCount)
        }
    }

    fun openRemainCells() {
        cells
            .filter { it.status == Status.CLOSE }
            .map { it.open() }
    }

    fun openAllMineCells() {
        cells
            .filterIsInstance<Mine>()
            .map { it.open() }
    }

    fun openAdjacentBlanksBy(blank: Blank) {
        blank.open()
        changeOpenAdjacentBlanks(getTargetBlanks(blank.coordinate))
    }

    private fun changeOpenAdjacentBlanks(list: List<Blank>) {
        list.forEach {
            it.open()
            changeOpenAdjacentBlanks(getTargetBlanks(it.coordinate))
        }
    }

    private fun getTargetBlanks(coordinate: Coordinate): List<Blank> {
        return Directions.getFourDirection()
            .map { direction ->
                findOrNull(coordinate.movedCoordinate(direction))
            }
            .filterIsInstance<Blank>()
            .filter { blank -> blank.status == Status.CLOSE }
    }
}
