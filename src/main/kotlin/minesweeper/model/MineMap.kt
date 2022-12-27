package minesweeper.model

class MineMap private constructor(
    val rowSize: Int,
    val columnSize: Int
) {
    private val cells = List(rowSize) { y -> List(columnSize) { x -> Cell(x, y) } }
        .flatten()
        .sortedWith(compareBy({ it.y }, { it.x }))

    init {
        require(rowSize >= MINIMUM_SIZE) { "행의 크기는 0이 될 수 없습니다." }
        require(columnSize >= MINIMUM_SIZE) { "열의 크기는 0이 될 수 없습니다." }
    }

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize &&
            cell.y in INIT_INDEX until INIT_INDEX + rowSize

    fun selectAllCells(): List<Cell> {
        return cells
    }

    fun selectRandomCells(cellCount: Int): Mines {
        val shuffledMines = cells
            .shuffled()
            .take(cellCount)
            .toSet()
        return Mines(shuffledMines)
    }

    companion object {
        const val INIT_INDEX = 0
        private const val MINIMUM_SIZE = 1
        fun of(height: Int, width: Int): MineMap {
            return MineMap(height, width)
        }
    }
}
