package minesweeper.domain

@JvmInline
value class Number private constructor(val value: Int) {

    fun dec(): Int {
        return value.dec()
    }

    fun inc(): Int {
        return value.inc()
    }

    companion object {
        fun of(data: String): Number {

            val value = data.toIntOrNull()
            require(value != null) {
                "데이터는 숫자이어야한다"
            }
            require(value > 0) {
                "데이터는 0보다 커야한다"
            }
            return Number(value)
        }
    }
}
