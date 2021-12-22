package domain

class Row(
    val cells: List<Cell>,
) {
    val width = cells.size

    fun isMine(position: Int): Boolean {
        return cells[position].isMine()
    }

    operator fun get(index: Int): Cell {
        return cells[index]
    }

    companion object {
        fun build(
            width: Int,
            currentHeight: Int,
            minesPosition: MinesPosition,
        ): Row {
            val widthRange = (0 until width)

            val cells = widthRange.map { x ->
                val position = Position(x, currentHeight)
                val mineCount = minesPosition.getMineCountByPosition(position)

                when (minesPosition.contains(position)) {
                    true -> MineCell()
                    false -> GeneralCell(mineCount, position)
                }
            }

            return Row(cells)
        }
    }
}
