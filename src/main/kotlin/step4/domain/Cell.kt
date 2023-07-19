package step4.domain

import step4.domain.CellType.UNKNOWN
import step4.domain.CellType.ZERO

class Cell(
    val cellType: CellType = ZERO,
    var isOpen: Boolean = false,
) {
    fun toMine() {
        check(cellType.isMine().not()) { "이미 지뢰로 변경된 cell은 다시 지뢰로 변경할 수 없습니다." }
    }

    fun open() {
        check(isOpen.not()) { "이미 오픈된 좌표는 다시 오픈할 수 없습니다." }
        isOpen = true
    }

    fun cellType(): CellType {
        if (isOpen) {
            return cellType
        }
        return UNKNOWN
    }
}
