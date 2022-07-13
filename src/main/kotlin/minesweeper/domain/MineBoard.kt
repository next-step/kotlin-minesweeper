package minesweeper.domain

import minesweeper.dto.MineBoardLength
import minesweeper.dto.MineCount

class MineBoard(
    cells: Map<Coordinate, Dot>
) {
    private val _cells: MutableMap<Coordinate, Dot> = cells.toMutableMap()
    val cells: Map<Coordinate, Dot>
        get() = _cells

    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }

    fun open(coordinate: Coordinate): Dot {
        val dot = _cells[coordinate] ?: throw IllegalArgumentException("해당 좌표는 존재하지 않습니다.")

        require(dot.isHidden) { "이미 오픈된 영역 입니다." }

        return when (dot) {
            is Land -> {
                openAround(coordinate)
                _cells[coordinate]!!
            }
            is Mine -> {
                _cells[coordinate] = dot.open()
                _cells[coordinate]!!
            }
        }
    }

    private fun openAround(coordinate: Coordinate) {
        val dot = _cells[coordinate] ?: return

        _cells[coordinate] = dot.open()

        if (dot.isZeroMineLand()) {
            CoordinateDirection.around(coordinate)
                .filter { _cells[it]?.isHidden ?: false }
                .forEach(::openAround)
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
            val cells = coordinates.randomBuryMine(count = mineCount)
            return MineBoard(cells = cells)
        }
    }
}
