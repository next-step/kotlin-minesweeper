package minesweeper.model

class MineMap private constructor(
    private val value: List<List<Cell>>
) : List<List<Cell>> by value {
    init {
        require(value.isNotEmpty())
        require(value.first().isNotEmpty())
    }

    val rowSize = value.size
    val columnSize = value.first().size

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize
                && cell.y in INIT_INDEX until INIT_INDEX + rowSize


    companion object {
        private const val INIT_INDEX = 0
        fun of(height: Int, width: Int): MineMap {
            return MineMap(List(height) { y -> List(width) { x -> Cell(x, y) } })
        }
    }
}
