package minesweeper.domain

@JvmInline
value class Position private constructor(val value: Int) {

    fun dec(): Int {
        return value.dec()
    }

    fun inc(): Int {
        return value.inc()
    }

    companion object {

        fun of(data: Int): Position {
            require(data >= 0) {
                "위치값은 0보다 커야한다"
            }
            return Position(data.dec())
        }
    }
}
