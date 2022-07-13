package minesweeper.domain

import minesweeper.dto.MineCount

sealed interface Dot {
    val status: DotStatus

    val isHidden: Boolean
        get() = status == DotStatus.HIDDEN

    fun isZeroMineLand(): Boolean = this is Land && mineCount.isZero()

    fun open(): Dot
}

data class Land(
    val mineCount: MineCount,
    override val status: DotStatus = DotStatus.HIDDEN
) : Dot {
    override fun open(): Dot = Land(mineCount = mineCount, status = DotStatus.OPEN)
}

data class Mine(
    override val status: DotStatus = DotStatus.HIDDEN
) : Dot {
    override fun open(): Dot = Mine(status = DotStatus.OPEN)
}

enum class DotStatus {
    OPEN,
    HIDDEN
}
