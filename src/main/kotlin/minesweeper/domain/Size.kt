package minesweeper.domain

data class Size(val value: Int) {
    constructor(value: String) : this(value.toInt())

    init {
        require(value in 1..100) { "1 이상 100 이하의 숫자여야만 합니다." }
    }

    operator fun times(other: Size): Size {
        return Size(value * other.value)
    }

    fun getNumbers(): List<Size> {
        return (1..value)
            .map { Size(it) }
    }

    operator fun div(height: Size): Size {
        return Size(value / height.value + 1)
    }

    operator fun rem(width: Size): Size {
        return Size(value % width.value + 1)
    }

    fun getRows(x: Size): List<Position> {
        return getNumbers()
            .map { Position(x, it) }
    }
}
