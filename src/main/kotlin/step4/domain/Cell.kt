package step4.domain

class Cell(
    val cellType: CellType,
    var isOpen: Boolean,
) {
    fun open() {
        check(isOpen.not()) { "이미 오픈된 좌표는 다시 오픈할 수 없습니다." }
    }
}
