package minesweeper.model

class MineMap private constructor(
    val rowSize: Int,
    val columnSize: Int
) {
    init {
        require(rowSize >= MINIMUM_SIZE) { "행의 크기는 0이 될 수 없습니다." }
        require(columnSize >= MINIMUM_SIZE) { "열의 크기는 0이 될 수 없습니다." }
    }

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize &&
            cell.y in INIT_INDEX until INIT_INDEX + rowSize

    companion object {
        const val INIT_INDEX = 0
        private const val MINIMUM_SIZE = 1
        fun of(height: Int, width: Int): MineMap {
            return MineMap(height, width)
        }
    }
}

fun getDefaultCellPool(rowSize: Int, columnSize: Int) =
    List(rowSize) { y -> List(columnSize) { x -> Cell(x, y) } }
        .flatten()
        .associateBy { Cell(it.x, it.y) }
        .toSortedMap(compareBy({ it.y }, { it.x }))
