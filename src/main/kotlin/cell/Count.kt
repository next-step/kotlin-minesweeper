package cell

@JvmInline
value class Count(val value: Int) {
    init {
        require(value >= 0) { "카운트 값은 0 이상이어야 합니다." }
    }
}
