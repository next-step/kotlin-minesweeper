package minesweeper.domain

@JvmInline
value class Positions(private val _positions: List<Position>) {

    val position: List<Position>
        get() = _positions.toList()

    companion object {
        fun of(width: Width, height: Height): Positions {
            return from(
                (0 until width.value).flatMap { x ->
                    (0 until height.value).map { y ->
                        Position(Row.from(x), Column.from(y))
                    }
                }
            )
        }

        fun from(positions: List<Position>): Positions {
            return Positions(positions.toList())
        }
    }
}
