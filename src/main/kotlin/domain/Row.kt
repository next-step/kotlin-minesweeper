package domain

@JvmInline
value class Row(val value: Int) {
    init {
        require(value > 0) {"높이는 0보다 큰 정수여야 합니다."}
    }
}