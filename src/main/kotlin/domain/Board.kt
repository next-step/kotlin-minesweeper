package domain

class Board(cells: List<Cell>) {
    private val _cells: MutableList<Cell> = cells.toMutableList()
    val cells: List<Cell>
        get() = _cells.toMutableList()

    val isOpenAllBlank: Boolean
        get() {
            val blanks = cells.filterIsInstance<Blank>()
            val openBlanks = blanks.filter { it.status == Status.OPEN }
            return blanks.size == openBlanks.size
        }

    fun isMineCell(coordinate: Coordinate): Boolean {
        return cells.find { it.coordinate == coordinate } is Mine
    }

    fun findOrNull(coordinate: Coordinate): Cell? {
        return cells.find { it.coordinate == coordinate }
    }

    fun markMinesAroundCount(boardInfo: BoardInfo) {
        val mineDetector = MineDetector(boardInfo, this)

        cells.filterIsInstance<Blank>().forEach() {
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

    fun getBlankCell(coordinate: Coordinate): Blank {
        val cell = findOrNull(coordinate)

        return if (cell is Blank) cell else throw IllegalArgumentException(ERROR_MESSAGE_NOT_EXIST_COORDINATE)
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

    companion object {
        private const val ERROR_MESSAGE_NOT_EXIST_COORDINATE = "존재하지 않는 좌표입니다."
    }
}
