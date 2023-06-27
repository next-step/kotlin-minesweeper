package minesweeper_refactor.view.model

@JvmInline
value class BlockRowsView(private val rows: List<BlockStateView>) {

    override fun toString(): String = rows.joinToString(separator = SEPARATOR) { it.exposureName }

    companion object {
        private const val SEPARATOR: String = " "
    }
}
