package minesweeper.domain.board

@JvmInline
value class Mine(private val value: Int = -1) : CellType {
    override fun getValue(): Int = value
}
