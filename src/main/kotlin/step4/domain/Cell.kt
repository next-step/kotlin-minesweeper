package step4.domain

import step4.domain.CellType.UNKNOWN

class Cell(
    val cellType: CellType,
    var isOpen: Boolean,
) {
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
