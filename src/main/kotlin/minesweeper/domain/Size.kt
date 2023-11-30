package minesweeper.domain

data class Size(val value: Int) {
    constructor(value: String) : this(value.toInt())

    init {
        require(value in 1..100) { "1 이상 100 이하의 숫자여야만 합니다." }
    }

    operator fun times(other: Size): Size {
        return Size(value * other.value)
    }

    fun getRandomNumbers(count: Size): List<Size> {
        return (1..value)
            .shuffled()
            .take(count.value)
            .map { Size(it) }
    }

    operator fun div(height: Size): Int {
        return value / height.value
    }

    operator fun rem(width: Size): Int {
        return value % width.value
    }
}
