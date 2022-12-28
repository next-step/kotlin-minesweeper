package minesweeper.state

import minesweeper.model.Point

sealed interface BlockState {
    val point: Point

    fun isMine(): Boolean
    fun mine(): BlockState

    data class Normal(override val point: Point) : BlockState {
        override fun isMine(): Boolean = false

        override fun mine(): BlockState = Mine(point)
    }

    data class Mine(override val point: Point) : BlockState {
        override fun isMine(): Boolean = true

        override fun mine(): BlockState = throw IllegalStateException("이미 지뢰입니다.")
    }
}
