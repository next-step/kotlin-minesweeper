package minesweeper.domain.cell

import minesweeper.domain.MineCount
import minesweeper.domain.cell.DotStatus.HIDDEN
import minesweeper.domain.cell.DotStatus.OPEN

sealed interface Dot {
    val status: DotStatus

    val isHidden: Boolean
        get() = status == HIDDEN

    fun isZeroMineLand(): Boolean = this is Land && mineCount.isZero()

    fun open(): Dot
}

data class Land(
    val mineCount: MineCount,
    override val status: DotStatus = HIDDEN
) : Dot {
    override fun open(): Dot = Land(mineCount = mineCount, status = OPEN)
}

data class Mine(
    override val status: DotStatus = HIDDEN
) : Dot {
    override fun open(): Dot = Mine(status = OPEN)
}

enum class DotStatus {
    OPEN,
    HIDDEN
}
