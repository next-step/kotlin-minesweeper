package minesweeper.view.model

@JvmInline
value class BlockRowsView(private val rows: List<String>) {

    override fun toString(): String = rows.joinToString(separator = SEPARATOR)

    companion object {
        private const val SEPARATOR: String = " "
    }
}
