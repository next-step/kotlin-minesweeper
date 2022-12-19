package domain

@JvmInline
value class Height(val value: Int) {
    init {
        require(value >= 0) { "높이는 음수가 될 수 없습니다." }
    }
}
