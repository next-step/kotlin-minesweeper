package minesweeper.domain

@JvmInline
value class Position(private val value: Int) : Comparable<Position> {
    init {
        require(value >= 0) { "0 보다 같거나 큰 수이여야 합니다." }
    }

    fun getNear(): List<Position> = listOfNotNull(
        if (this.value > 0) this.dec() else null,
        this,
        this.inc()
    )

    override fun compareTo(other: Position): Int =
        this.value.compareTo(other.value)

    private fun inc(): Position = Position(value.inc())

    private fun dec(): Position = Position(value.dec())
}
