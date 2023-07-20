package step4.domain.cell

import step4.domain.cell.CellType.MINE
import step4.domain.cell.CellType.UNKNOWN
import step4.domain.cell.CellType.ZERO

class Cell(
    var cellType: CellType = ZERO,
    var isOpen: Boolean = false,
) {
    fun toMine() {
        check(cellType.isMine().not()) { "이미 지뢰로 변경된 cell은 다시 지뢰로 변경할 수 없습니다." }
        this.cellType = MINE
    }

    fun isMine(): Boolean = cellType.isMine()

    fun isZero(): Boolean = cellType.isZero()

    fun open() {
        check(isOpen.not()) { "이미 오픈된 좌표는 다시 오픈할 수 없습니다." }
        isOpen = true
    }

    fun changeCellType(cellType: CellType) {
        this.cellType = cellType
    }

    fun cellType(): CellType {
        if (isOpen) {
            return cellType
        }
        return UNKNOWN
    }
}
