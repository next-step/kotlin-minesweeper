package domain

@JvmInline
value class Row(val value: Int) {
    init {
        require(value > 0) { "높이는 1 이상이어야 합니다." }
    }

    operator fun times(width: Column): Number {
        return Number(value.times(width.value))
    }
}
