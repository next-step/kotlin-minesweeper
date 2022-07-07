package minesweeper.domain

import minesweeper.domain.cell.Coordinate
import minesweeper.domain.cell.Coordinates
import minesweeper.domain.cell.Dot

class MineBoard(
    val cells: Map<Coordinate, Dot>,
) {
    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }

    fun open(coordinate: Coordinate): Dot =
        cells[coordinate] ?: throw IllegalArgumentException("해당 좌표는 존재하지 않습니다.")

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
