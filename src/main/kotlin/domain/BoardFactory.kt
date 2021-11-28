package domain

object BoardFactory {
    fun create(
        width: Width,
        height: Height,
        minesPosition: MinesPosition,
    ): Board {
        val heightRange = (0 until height.value)
        val rows = heightRange.map { currentHeight ->
            createRow(width.value, currentHeight, minesPosition)
        }

        return Board(rows)
    }

    private fun createRow(
        width: Int,
        currentHeight: Int,
        minesPosition: MinesPosition,
    ): Row {
        val widthRange = (0 until width)
        val cells = widthRange.map { x ->
            createCell(
                Position(x, currentHeight),
                minesPosition,
            )
        }

        return Row(cells)
    }

    private fun createCell(position: Position, minesPosition: MinesPosition): Cell {
        return when (minesPosition.contains(position)) {
            true -> MineCell()
            false -> GeneralCell()
        }
    }
}
