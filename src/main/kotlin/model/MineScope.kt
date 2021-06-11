package model

import model.board.Row

data class MineScope(val heightRange: IntRange, val widthRange: IntRange) {
    constructor(position: Position, height: Int, width: Int) :
            this(
                position.heightMinus(1).heightIndex..(position.heightIndex + 1).coerceAtMost(height - 1),
                position.widthMinus(1).widthIndex..(position.widthIndex + 1).coerceAtMost(width - 1)
            )

    fun countMine(rows: List<Row>): Int =
        heightRange.sumBy { heightIndex -> rows[heightIndex].countMine(widthRange) }
}