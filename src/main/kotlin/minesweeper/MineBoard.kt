package minesweeper

class MineBoard(
    val cells: List<Cell>,
) {
    fun placeMine(mineCount: Int) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}이상이어야 합니다." }
        require(mineCount < cells.size) { "지뢰 갯수는 현재 cell크기 ${cells.size}보다 작은 값을 입력하여야 합니다." }
        check(cells.none { it.isMine() }) { "이미 지뢰가 배치되어 있습니다." }
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 1
    }
}
