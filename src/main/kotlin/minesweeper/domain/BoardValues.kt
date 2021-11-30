package minesweeper.domain

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > 0) { "Height는 양의 정수여야합니다." }
    }
}

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > 0) { "Width는 양의 정수여야합니다." }
    }
}

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value > 0) { "지뢰 갯수는 양의 정수여야합니다." }
    }
}
