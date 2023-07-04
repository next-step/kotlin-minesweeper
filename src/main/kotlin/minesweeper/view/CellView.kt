package minesweeper.view

import minesweeper.domain.cell.CellType

enum class CellView(
    val cellType: CellType,
    val printView: String,
) {
    NONE_CLICK_CELL(CellType.UNKNOWN, "C"),
    MINE_CELL(CellType.MINE, "*"),
    ;

    companion object {
        fun from(cellType: CellType): CellView = values().firstOrNull { it.cellType == cellType }
            ?: throw IllegalArgumentException("뷰에서 지원하지 않는 cell 타입입니다.")
    }
}
