package minesweeper

typealias CellKey = Int

class Cells(private val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { it.isMine }

    fun detectMines() {
        values.values.forEach { cell ->
            val neighborMineCount = neighborsMineCount(cell.position)
            cell.determineCell(neighborMineCount)
        }
    }

    fun neighborsMineCount(position: Position): Int {
        return Direction.neighbors(position)
            .mapNotNull { values[it.key()] }
            .count { it.isMine }
    }

    fun assignMinesToCells(minePositions: List<Position>): Cells {
        return Cells(
            values.mapValues { (_, cell) ->
                if (minePositions.contains(cell.position)) {
                    Cell.MineCell(cell.position)
                } else {
                    cell
                }
            },
        )
    }

    fun checkMine(position: Position): Boolean {
        return at(position).isMine
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

    private fun at(position: Position): Cell {
        return values[position.key()] ?: throw IllegalArgumentException("셀이 존재하지 않습니다.")
    }

    companion object {
        fun create(otherCells: List<Cell>): Cells {
            return Cells(otherCells.associateBy { it.position.key() })
        }
    }
}
