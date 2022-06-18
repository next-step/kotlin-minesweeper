package minesweeper.model

@JvmInline
value class Mark private constructor(
    private val mark: Char
) {
    fun isMine() = mark == MINE_MARK

    companion object {
        private const val MINE_MARK = '*'
        private const val CLOSE_MARK = 'C'

        fun mine() = Mark(MINE_MARK)

        fun close() = Mark(CLOSE_MARK)
    }
}
