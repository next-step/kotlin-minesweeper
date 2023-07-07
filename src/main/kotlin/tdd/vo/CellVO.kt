package tdd.vo

import tdd.domain.Cell

class CellVO(
    val cell: String,
) {
    companion object {
        operator fun invoke(cell: Cell) = CellVO(
            if (cell.isOpened()) cell.aroundMineCount().toString() else "C"
        )
    }
}
