package minesweeper.domain.board

@JvmInline
value class NotMine(private val value: Int = 0) : CellType {
    override fun getValue(): Int = value
}
