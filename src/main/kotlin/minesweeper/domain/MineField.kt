package minesweeper.domain

import minesweeper.domain.field.Field

class MineField(
    val fields: List<Field>
) {
    init {
        require(fields.isNotEmpty()) { "지뢰판은 비어있을수 없습니다." }
    }
}
