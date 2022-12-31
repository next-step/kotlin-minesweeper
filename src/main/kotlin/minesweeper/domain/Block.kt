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

    constructor(point: Point, block: (Point) -> Int = { 0 }) :
        this(Normal(point, block.invoke(point)))

    fun isMine(): Boolean = state.isMine()
    fun mine() {
        _state = state.mine()
    }

    override fun toString(): String = state.toString()
}
