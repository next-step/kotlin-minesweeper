package minesweeper.domain

import minesweeper.domain.cell.*

class MineBoard(
    val cells: Map<Coordinate, Dot>,
) {
    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }

    fun open(coordinate: Coordinate): Dot  {
        return cells[coordinate]?.let { dot ->
            if (dot is Land) {
                dot.open()
                CoordinateDirection.around(coordinate).forEach(::openAround)
            }

            return dot
        } ?: throw IllegalArgumentException("해당 좌표는 존재하지 않습니다.")
    }

    private fun openAround(coordinate: Coordinate): Unit = cells[coordinate].let { dot ->
        if (dot is Land && dot.isHidden) {
            dot.open()
            CoordinateDirection.around(coordinate).forEach(::openAround)
        }
    }

    fun remainHiddenLands(): Boolean = cells.values.filterIsInstance<Land>().any { it.isHidden }

    companion object {
        fun create(
            height: MineBoardLength,
            width: MineBoardLength,
            mineCount: MineCount,
        ): MineBoard {
            val coordinates = Coordinates.from(height = height, width = width)
            val cells = coordinates.randomMine(count = mineCount)
            return MineBoard(cells = cells)
        }
    }
}
