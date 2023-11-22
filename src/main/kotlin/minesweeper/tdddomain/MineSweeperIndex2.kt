package minesweeper.tdddomain

import minesweeper.domain.MineStatus
import minesweeper.domain.Position
import minesweeper.domain.PositionStatus

class MineSweeperIndex2(val position: Position, val mineStatus: MineStatus) {
    var openStatus: PositionStatus
        private set

    init {
        openStatus = PositionStatus.CLOSED
    }
}
