package minesweeper.domain

import minesweeper.domain.rule.BoardGenerationRule

class Board(private val metadata: BoardMetadata, rule: BoardGenerationRule) {
    private val rows: Rows

    init {
        val rawBoard = rule.generate(metadata)
        rows = Rows(rawBoard.map { Row(it) })
    }

    fun at(row: Int, col: Int): Cell {
        require(row >= 0 && row < metadata.height) { "존재하지 않는 좌표입니다." }
        require(col >= 0 && col < metadata.width) { "존재하지 않는 좌표입니다." }

        return rows.at(row, col)
    }
}
