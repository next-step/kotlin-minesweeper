package domain

@JvmInline
value class Width(val value: Int) {
    init {
        require(value >= 0) { "너비는 음수가 될 수 없습니다." }
    }
}
