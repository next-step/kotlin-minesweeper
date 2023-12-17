package minesweeper.domain.cell

sealed interface Cell {
    val position: Position

    data class Mine(
        override val position: Position,
    ) : Cell

    data class Clear(
        override val position: Position,
        val mineCount: MineCount,
        private var isOpened: Boolean = false,
    ) : Cell {

        fun open() {
            isOpened = true
        }

        fun isOpened(): Boolean = isOpened
    }
}
