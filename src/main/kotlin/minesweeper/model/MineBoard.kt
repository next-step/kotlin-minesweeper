package minesweeper.model

class MineBoard(
    private val board: List<Cells>
) {

    override fun toString() = board.joinToString(CELLS_SEPARATOR)

    companion object {
        private const val CELLS_SEPARATOR = "\n"
    }
}
