package minesweeper.domain.cell

import minesweeper.domain.MineCount
import minesweeper.domain.cell.DotStatus.*

sealed interface Dot {
    val status: DotStatus

    val isHidden: Boolean
        get() = status == HIDDEN

    fun open(): Dot
}

data class Land(
    val mineCount: MineCount,
    override val status: DotStatus = HIDDEN
) : Dot {
    override fun open(): Dot {
        require(status == HIDDEN) { "이미 오픈된 영역 입니다." }
        return Land(
            mineCount = mineCount,
            status = OPEN
        )
    }
}

data class Mine(
    override val status: DotStatus = HIDDEN
) : Dot {
    override fun open(): Dot {
        require(status == HIDDEN) { "이미 오픈된 영역 입니다." }
        return Mine(
            status = OPEN
        )
    }
}

enum class DotStatus {
    OPEN,
    HIDDEN
}
