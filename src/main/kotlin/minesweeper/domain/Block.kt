package minesweeper.domain

import minesweeper.model.Point
import minesweeper.model.Point.Companion.MIN_VALUE
import minesweeper.state.BlockState
import minesweeper.state.BlockState.Normal

class Block(initialState: BlockState) {

    private var _state: BlockState = initialState
    val state: BlockState
        get() = _state

    val point: Point
        get() = state.point

    constructor(point: Point, block: (Point) -> Int = { 0 }) :
        this(Normal.of(point, block.invoke(point))) {

        require(point.x >= MIN_VALUE) { "x 좌표는 $MIN_VALUE 이상이어야 합니다." }
        require(point.y >= MIN_VALUE) { "y 좌표는 $MIN_VALUE 이상이어야 합니다." }
    }

    fun isMine(): Boolean = state.isMine()

    fun isOpen(): Boolean = state.isOpen()
    fun mine() {
        _state = state.mine()
    }

    fun open(): Boolean {
        if (state.isMine()) return false
        _state = state.open()
        return true
    }

    override fun toString(): String = state.toString()
}
