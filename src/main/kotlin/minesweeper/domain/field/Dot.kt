package minesweeper.domain.field

sealed interface Dot {
    var status: DotStatus

    fun open() {
        require(status == DotStatus.HIDDEN) { "이미 오픈된 지점입니다." }

        status = DotStatus.OPEN
    }
}

data class Mine(override var status: DotStatus = DotStatus.HIDDEN) : Dot

data class NonMine(val mineCount: Int, override var status: DotStatus = DotStatus.HIDDEN) : Dot {
    companion object {
        private const val DEFAULT_MINE_COUNT = 0

        val DEFAULT = NonMine(DEFAULT_MINE_COUNT)
    }
}

enum class DotStatus {
    OPEN,
    HIDDEN;
}
