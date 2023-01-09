package domain

@JvmInline
value class MinesAroundCount(val value: Int) {
    init {
        require(value >= 0) { "지뢰 개수는 0 이상이어야 합니다." }
    }

    companion object {
        val ZERO = MinesAroundCount(0)
    }
}
