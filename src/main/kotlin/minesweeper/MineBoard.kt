package minesweeper

class MineBoard(
    val cells: List<Cell>,
) {
    fun placeMine() {
        check(cells.none { it.isMine() }) { "이미 지뢰가 배치되어 있습니다." }
    }
}
