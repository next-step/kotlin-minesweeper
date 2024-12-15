package minesweeper.domain

class Board(
    val cells: Set<Cell>,
) {
    init {
        require(cells.isNotEmpty()) { "빈 판을 생성할 수 없다" }
    }
}
