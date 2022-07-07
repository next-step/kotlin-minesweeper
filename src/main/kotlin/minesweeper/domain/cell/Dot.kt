package minesweeper.domain.cell

import minesweeper.domain.MineCount
import minesweeper.domain.cell.DotStatus.*

sealed interface Dot {
    var status: DotStatus

    val isOpen: Boolean
        get() = status == OPEN

    val isHidden: Boolean
        get() = status == HIDDEN

    fun open() {
        require(status == HIDDEN) { "이미 오픈된 영역 입니다." }
        status = OPEN
    }
}

data class Land(
    val mineCount: MineCount,
    override var status: DotStatus = HIDDEN
) : Dot

data class Mine(
    override var status: DotStatus = HIDDEN
) : Dot

enum class DotStatus {
    OPEN,
    HIDDEN
}
