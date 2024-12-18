package minesweeper

class Cells(private val values: MutableMap<Position, CellType>) {
    val mineCount: Int
        get() = values.values.count { it.isMine() }

    fun checkMine(position: Position): Boolean {
        return at(position).isMine()
    }

    fun rowSize(): Int {
        return values.keys
            .filter { it.x == 0 }
            .size
    }

    fun rowAt(rowIndex: Int): List<CellType>? {
        return values.entries
            .filter { it.key.y == rowIndex }
            .map { it.value }
            .takeIf { it.isNotEmpty() }
    }

    private fun at(position: Position): CellType {
        return values[position] ?: throw IllegalArgumentException("존재 하지 않는 위치 입니다.")
    }
}
