package model

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value >= 0) { throw IllegalArgumentException("지뢰 갯수는 0 이상이어야합니다.") }
    }
}
