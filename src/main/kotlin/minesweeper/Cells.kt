package minesweeper

typealias CellKey = Int

class Cells(private val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { it.isMine }

    fun detectMines() {
        values.values.forEach { cell ->
            cell.determineCell(this)
        }
    }

    fun neighborsMineCount(position: Position): Int {
        return Direction.neighbors(position)
            .mapNotNull { values[it.key()] }
            .count { it.isMine }
    }

    fun assignMinesToCells(minePositions: List<Position>): Cells {
        return Cells(values.mapValues { (_, cell) ->
            if (minePositions.contains(cell.position)) {
                cell.withMine()
            } else {
                cell
            }
        })
    }

    fun checkMine(position: Position): Boolean {
        return at(position).isMine()
    }

    fun rowSize(): Int {
        return values.values
            .filter { it.x == 0 }
            .size
    }

    fun rowAt(rowIndex: Int): List<Cell> {
        return values.values
            .filter { it.matchRowIndex(rowIndex) }
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
