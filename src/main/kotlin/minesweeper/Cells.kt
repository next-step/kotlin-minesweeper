package minesweeper

//class Cells(private val values: MutableMap<Position, CellType>) {
//    val mineCount: Int
//        get() = values.values.count { it.isMine() }
//
//    fun checkMine(position: Position): Boolean {
//        return at(position).isMine()
//    }
//
//    fun rowSize(): Int {
//        return values.keys
//            .filter { it.x == 0 }
//            .size
//    }
//
//    fun rowAt(rowIndex: Int): List<CellType>? {
//        return values.entries
//            .filter { it.key.y == rowIndex }
//            .map { it.value }
//            .takeIf { it.isNotEmpty() }
//    }
//
//    private fun at(position: Position): CellType {
//        return values[position] ?: throw IllegalArgumentException("존재 하지 않는 위치 입니다.")
//    }
//}


class Cells(private val values: Map<Int, Cell>) {
    val mineCount: Int
        get() = values.values.count { it.isMine }

    fun checkMine(position: Position): Boolean {
        return at(position).isMine()
    }

    // TODO : rowSize, rowAt 는 사실 Dimensions 정보인데 이렇게 할 이유가 있을까?
    fun rowSize(): Int {
        return values.values
            .filter { it.x == 0 }
            .size
    }

    fun rowAt(rowIndex: Int): List<CellType> {
        return values.values
            .filter { it.matchRowIndex(rowIndex) }
            .map { it.type }
    }

    private fun at(position: Position): CellType {
        return values[position.key()]?.type ?: throw IllegalArgumentException("존재 하지 않는 위치 입니다.")
    }

    companion object {
        fun create(otherCells: List<Cell>): Cells {
            return Cells(otherCells.associateBy { it.position.key() })
        }
    }
}
