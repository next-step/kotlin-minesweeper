package minesweeper.domain.cell

sealed interface Cell {
    val position: Position

    data class Mine(
        override val position: Position,
    ) : Cell

    data class Clear(
        override val position: Position,
        val mineCount: MineCount = MineCount.ZERO,
        private var isOpened: Boolean = false
    ) : Cell {
        fun open(): Cell {
            isOpened = true
            return this
        }

        fun isOpened(): Boolean = isOpened
    }
}
