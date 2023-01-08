package minesweeper.domain

import minesweeper.domain.State.CLOSE
import minesweeper.domain.State.OPEN

class Coordinate(private val type: CoordinateType = CoordinateType.NONE) {

    var count: Int = 0
        private set

    private var state: State = CLOSE

    fun counting() = count++

    fun open() {
        state = OPEN
    }

    fun isOpen(): Boolean = state == OPEN

    fun isMine(): Boolean = type == CoordinateType.MINE
}
