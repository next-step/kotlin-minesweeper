package minesweeper.model

sealed class Cell {

    abstract val position: Position

    val row: Row get() = position.row
    val column: Column get() = position.column

    fun mine(): Cell = when (this) {
        is Mine -> this
        else -> Mine(position)
    }

    data class Number(
        val adjustMineCount: MineCount,
        override val position: Position
    ) : Cell() {

        init {
            require(adjustMineCount != MineCount.ZERO)
        }

        fun increment(): Cell = copy(adjustMineCount = adjustMineCount.increment())
    }

    data class Zero(override val position: Position) : Cell()

    data class Mine(override val position: Position) : Cell()
}
