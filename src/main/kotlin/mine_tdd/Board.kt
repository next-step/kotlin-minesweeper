package mine_tdd

class Board(
    val width: Width,
    val height: Height
) {

    fun row(): Int = width.value()
    fun column(): Int = height.value()

    companion object {
        fun createBoard(width: Width, height: Height): Board {
            return Board(width, height)
        }
    }
}
