package domain

@JvmInline
value class Column(val value: Int) {
    init {
        require(value > 0) { "너비는 1 이상이여야 합니다." }
    }
}
