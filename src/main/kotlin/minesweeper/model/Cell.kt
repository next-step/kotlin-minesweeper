package minesweeper.model

@JvmInline
value class Cell private constructor(
    private val type: CellType,
) {
    fun isMine() = type.isMine()

    companion object {
        fun mine() = Cell(CellType.MINE)

        fun close() = Cell(CellType.NON_MINE)
    }
}
