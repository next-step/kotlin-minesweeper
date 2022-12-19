package domain

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value >= 0) { "지뢰 수는 음수가 될 수 없습니다." }
    }
}
