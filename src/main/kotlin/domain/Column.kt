package domain

@JvmInline
value class Column(val value: Int) {
    init {
        require(value > 0) {"너비는 0보다 큰 정수여야 합니다."}
    }
}