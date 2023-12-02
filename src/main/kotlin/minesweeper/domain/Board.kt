package minesweeper.domain

import minesweeper.domain.rule.MineGenerationRule

class Board(private val metadata: BoardMetadata, rule: MineGenerationRule) {
    private val rows: Map<Coordinate, Cell>
    private val countsOfAroundMines: MutableMap<Coordinate, Int> = mutableMapOf()

    init {
        rows = rule.generate(metadata)
    }

    fun at(row: Int, col: Int): Cell {
        return rows[Coordinate(row, col)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }

    fun countOf(row: Int, col: Int): Int {
        return countsOfAroundMines[Coordinate(row, col)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }

    fun countAllAroundMine() {
        (0 until metadata.width).flatMap { row ->
            (0 until metadata.height)
                .filter { col -> at(row, col).type != CellType.MINE }
                .map { col ->
                    this.countsOfAroundMines[Coordinate(row, col)] = countAroundMine(row, col)
                }
        }
    }

    private fun countAroundMine(row: Int, col: Int): Int {
        var mineCount = 0
        val currentCoordinate = Coordinate(row, col)
        for (aroundCoordinate in AROUND_COORDINATES) {
            val nextCoordinate = currentCoordinate + aroundCoordinate
            if (nextCoordinate.isOutOfBound(metadata.height, metadata.width)) continue
            if (at(nextCoordinate.row, nextCoordinate.col).type == CellType.MINE) {
                mineCount++
            }
        }
        return mineCount
    }

    companion object {
        private val AROUND_COORDINATES = listOf(
            Coordinate(-1, -1),
            Coordinate(-1, 0),
            Coordinate(-1, 1),
            Coordinate(0, -1),
            Coordinate(0, 1),
            Coordinate(1, -1),
            Coordinate(1, 0),
            Coordinate(1, 1)
        )
    }
}
