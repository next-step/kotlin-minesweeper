package minesweeper.domain

import minesweeper.domain.cell.*

class MineBoard(
    val cells: Map<Coordinate, Dot>,
) {
    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }

    fun open(coordinate: Coordinate): Dot {
        val dot = cells[coordinate] ?: throw IllegalArgumentException("해당 좌표는 존재하지 않습니다.")

        return when (dot) {
            is Land -> dot.apply {
                dot.open()
                CoordinateDirection.around(coordinate).forEach(::openAround)
            }
            is Mine -> {
                dot.open()
                dot
            }
        }
    }

    private fun openAround(coordinate: Coordinate) {
        val dot = cells[coordinate] ?: return

        if (dot is Land && dot.isHidden) {
            dot.open()
            CoordinateDirection.around(coordinate).forEach(::openAround)
        }
    }

    fun remainHiddenLands(): Boolean = cells.values.filterIsInstance<Land>().any { it.isHidden }

    fun nonExistOpenedMine(): Boolean = cells.values.filterIsInstance<Mine>().all { it.isHidden }

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
