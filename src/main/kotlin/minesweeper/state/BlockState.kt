package minesweeper.state

import minesweeper.model.Point

sealed interface BlockState {
    val point: Point
    val countOfSurroundMines: Int

    fun isMine(): Boolean
    fun mine(): BlockState

    data class Normal(override val point: Point, override val countOfSurroundMines: Int = 0) : BlockState {
        override fun isMine(): Boolean = false

        override fun mine(): BlockState = Mine(point)

        override fun toString(): String = "$countOfSurroundMines"
    }

    data class Mine(override val point: Point) : BlockState {
        override val countOfSurroundMines: Int
            get() = throw IllegalStateException("지뢰는 주변 지뢰 개수를 가지지 않습니다.")

        override fun isMine(): Boolean = true

        override fun mine(): BlockState = throw IllegalStateException("이미 지뢰입니다.")

        override fun toString(): String = "*"
    }
}
