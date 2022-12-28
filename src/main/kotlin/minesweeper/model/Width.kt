package minesweeper.model

@JvmInline
value class Width(val value: Int) {
    init {
        require(value >= MIN_VALUE) { "너비는 ${MIN_VALUE}개 이상이어야 합니다." }
    }

    companion object {
        const val MIN_VALUE = 1
    }
}

@JvmInline
value class Height(val value: Int) {
    init {
        require(value >= MIN_VALUE) { "높이는 ${MIN_VALUE}개 이상이어야 합니다." }
    }

    companion object {
        const val MIN_VALUE = 1
    }
}

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value >= MIN_VALUE) { "지뢰 개수는 ${MIN_VALUE}개 이상이어야 합니다." }
    }

    companion object {
        const val MIN_VALUE = 1
    }
}
