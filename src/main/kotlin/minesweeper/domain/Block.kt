package minesweeper.domain

import minesweeper.model.Point
import minesweeper.state.BlockState
import minesweeper.state.BlockState.Normal

class Block(initialState: BlockState) {

    private var _state: BlockState = initialState
    val state: BlockState
        get() = _state

    val point: Point
        get() = state.point

    constructor(height: Int, width: Int) : this(Normal(Point(height, width)))

    fun isMine(): Boolean = state.isMine()
    fun mine() {
        _state = state.mine()
    }

    override fun toString(): String = state.toString()
}
