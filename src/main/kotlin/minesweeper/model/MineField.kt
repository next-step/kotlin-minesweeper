package minesweeper.model

class MineField(
    private val field: List<Marks>
) {

    override fun toString() = field.joinToString(MARKS_SEPARATOR)

    companion object {
        private const val MARKS_SEPARATOR = "\n"
    }
}
