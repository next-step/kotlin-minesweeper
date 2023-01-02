package minesweeper.state

import minesweeper.model.Point

sealed interface BlockState {
    val point: Point
    val countOfSurroundMines: Int

    fun isMine(): Boolean
    fun mine(): BlockState
    fun isOpen(): Boolean
    fun open(): BlockState

    sealed interface Normal : BlockState {
        data class Open(
            override val point: Point,
            override val countOfSurroundMines: Int
        ) : Normal {

            override fun isMine(): Boolean = false

            override fun mine(): BlockState = Mine(point)

            override fun isOpen(): Boolean = true

            override fun open(): BlockState = throw IllegalStateException("이미 열려있는 블록입니다.")

            override fun toString(): String = "$countOfSurroundMines"
        }

        data class Closed(
            override val point: Point,
            override val countOfSurroundMines: Int
        ) : Normal {

            override fun isMine(): Boolean = false

            override fun mine(): BlockState = Mine(point)

            override fun isOpen(): Boolean = false

            override fun open(): BlockState = Open(point, countOfSurroundMines)

            override fun toString(): String = "C"
        }

        companion object {
            fun of(point: Point, countOfSurroundMines: Int = 0): Normal =
                Closed(point, countOfSurroundMines)
        }
    }

    data class Mine(override val point: Point) : BlockState {
        override val countOfSurroundMines: Int
            get() = throw IllegalStateException("지뢰는 주변 지뢰 개수를 가지지 않습니다.")

        override fun isMine(): Boolean = true

        override fun mine(): BlockState = throw IllegalStateException("이미 지뢰입니다.")
        override fun isOpen(): Boolean = throw IllegalStateException("지뢰는 열리지 않습니다.")
        override fun open(): BlockState = throw IllegalStateException("지뢰는 열 수 없습니다.")

        override fun toString(): String = "C"
    }
}
