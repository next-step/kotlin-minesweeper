package minesweeper.model

class MineMap private constructor(
    private val value: List<List<Cell>>
) : List<List<Cell>> by value {
    init {
        require(value.isNotEmpty()) { "행의 크기는 0이 될 수 없습니다." }
        require(value.first().isNotEmpty()) { "열의 크기는 0이 될 수 없습니다." }
    }

    val rowSize = value.size
    val columnSize = value.first().size

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize && cell.y in INIT_INDEX until INIT_INDEX + rowSize

    fun selectRandomMines(mineCount: Int): Mines {
        val shuffledMines = value.flatten()
            .shuffled()
            .take(mineCount)
            .toSet()
        return Mines(shuffledMines)
    }

    companion object {
        private const val INIT_INDEX = 0
        fun of(height: Int, width: Int): MineMap {
            return MineMap(List(height) { y -> List(width) { x -> Cell(x, y) } })
        }
    }
}
