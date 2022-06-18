package minesweeper.model

class Marks(
    private val marks: List<Mark>
) {
    val size
        get() = marks.size

    val mineCount
        get() = marks.count { it.isMine() }

    override fun toString() = marks.joinToString(MARK_SEPARATOR)

    companion object {
        private const val MARK_SEPARATOR = " "
    }
}
