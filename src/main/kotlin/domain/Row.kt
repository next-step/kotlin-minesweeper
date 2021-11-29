package domain

class Row(
    val cells: List<Cell>,
) {
    val width = cells.size

    fun isMine(position: Int): Boolean {
        return cells[position].isMine()
    }

    companion object {
        fun build(
            width: Int,
            currentHeight: Int,
            minesPosition: MinesPosition,
        ): Row {
            val widthRange = (0 until width)
            val cells = widthRange.map { x ->
                when (minesPosition.contains(Position(x, currentHeight))) {
                    true -> MineCell()
                    false -> GeneralCell()
                }
            }

            return Row(cells)
        }
    }
}
