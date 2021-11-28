package mine.cell

/**
 * cell 관리
 * */
class Cell(val position: Position) {
    lateinit var type: CellType
        private set

    fun typeOf(type: CellType) {
        this.type = type
    }
}
