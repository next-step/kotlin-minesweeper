package minesweeper.application.dto

import minesweeper.domain.field.Dot

data class MineFieldView(
    val rows: List<Row>,
)

data class Row(
    val value: List<Dot>
)
