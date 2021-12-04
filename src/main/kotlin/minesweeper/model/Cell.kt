package minesweeper.model

sealed class Cell {

    abstract val position: Position

    val row: Row get() = position.row
    val column: Column get() = position.column

    fun mine(): Cell = when (this) {
        is Mine -> this
        else -> Mine(position)
    }

    abstract fun increment(): Cell

    data class Number(
        val adjustMineCount: MineCount,
        override val position: Position
    ) : Cell() {

        init {
            require(adjustMineCount != MineCount.ZERO)
        }

        override fun increment(): Cell = copy(adjustMineCount = adjustMineCount.increment())
    }

    data class Zero(override val position: Position) : Cell() {

        override fun increment(): Cell = Number(MineCount.valueOf(1), position)
    }

    data class Mine(override val position: Position) : Cell() {

        override fun increment(): Cell = this
    }
}
