package minesweeper.domain

@JvmInline
value class CountOfLandmines(val value: Int) {
    init {
        require(value >= 0) {
            "지뢰 갯수는 0 개 이상이어야 합니다: count=$value"
        }
    }
}
