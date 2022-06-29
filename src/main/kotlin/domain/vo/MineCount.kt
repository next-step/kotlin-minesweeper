package domain.vo

@JvmInline
value class MineCount(val value: Int) {

    init {
        require(value > 0) { "지뢰 개수는 1 이상이어야 합니다" }
    }
}
