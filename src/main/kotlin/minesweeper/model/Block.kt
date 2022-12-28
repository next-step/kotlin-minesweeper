package minesweeper.model

import minesweeper.state.BlockState

data class Block(val state: BlockState) {
    constructor(height: Int, width: Int) : this(BlockState.Normal(Point(height, width)))
    val point: Point
        get() = state.point

    fun isMine(): Boolean = state.isMine()
}

data class Point(val x: Int, val y: Int) {
    init {
        require(x >= MIN_VALUE) { "x 좌표는 $MIN_VALUE 이상이어야 합니다." }
        require(y >= MIN_VALUE) { "y 좌표는 $MIN_VALUE 이상이어야 합니다." }
    }

    companion object {
        const val MIN_VALUE = 0
    }
}
