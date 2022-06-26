package vo

@JvmInline
value class Width(val value: Int) {

    init {
        require(value > 0) { "너비는 1 이상이어야 합니다" }
    }
}
