package minesweeper.model

sealed class Cell {

    abstract val position: Position

    val row: Row get() = position.row
    val column: Column get() = position.column

    fun mine(): Cell = when (this) {
        is Mine -> this
        else -> Mine(position)
    }

    abstract val isVisible: Boolean

    abstract val isZero: Boolean

    abstract fun increment(): Cell

    abstract fun tryOpen(): Cell

    data class Number(
        val adjustMineCount: MineCount,
        override val position: Position,
        override val isVisible: Boolean = false,
    ) : Cell() {

        override val isZero: Boolean = adjustMineCount == MineCount.ZERO

        override fun increment(): Cell = copy(adjustMineCount = adjustMineCount.increment())

        override fun tryOpen(): Cell = copy(isVisible = true)
    }

    data class Mine(override val position: Position) : Cell() {

        override val isVisible: Boolean = false

        override val isZero: Boolean = false

        override fun increment(): Cell = this

        override fun tryOpen(): Cell = this
    }

    companion object {
        fun zero(position: Position): Cell = Number(MineCount.ZERO, position, false)
    }
}
