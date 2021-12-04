package minesweeper.model

class Board(val cells: Cells) {

    val width: Width = Width.valueOf(cells.maxColumnOrNull()?.value)

    val height: Height = Height.valueOf(cells.maxRowOrNull()?.value)

    fun mine(position: Position): Board = cells
        .mine(position)
        .incrementAll(position.asDirections())
        .let { Board(it) }

    fun isMine(position: Position): Boolean = cells.isMine(position)

    fun tryOpen(position: Position): Board = Board(cells.tryOpen(position))

    fun isAllOpened(): Boolean = cells.isAllOpened()

    companion object {
        val EMPTY = Board(Cells.EMPTY)

        fun create(width: Width, height: Height): Board {
            if (width == Width.ZERO || height == Height.ZERO) {
                return EMPTY
            }
            val positions = Position.list(width, height)
            val cells: List<Cell> = positions.map { position -> Cell.zero(position) }
            return Board(Cells(cells))
        }
    }
}
