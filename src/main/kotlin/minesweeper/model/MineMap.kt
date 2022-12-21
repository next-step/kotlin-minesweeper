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

    companion object {
        fun of(height: Int, width: Int): MineMap {
            return MineMap(List(height) { y -> List(width) { x -> Cell(x, y) } })
        }
    }
}
