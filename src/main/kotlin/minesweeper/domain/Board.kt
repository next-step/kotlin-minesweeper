package minesweeper.domain

import minesweeper.domain.rule.MineGenerationRule

class Board(metadata: BoardMetadata, rule: MineGenerationRule) {
    private val rows: Map<Coordinate, Cell>

    init {
        rows = rule.generate(metadata)
    }

    fun at(x: Int, y: Int): Cell {
        return rows[Coordinate(x, y)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }
}
