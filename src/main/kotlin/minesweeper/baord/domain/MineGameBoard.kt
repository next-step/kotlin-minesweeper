package minesweeper.baord.domain

class MineGameBoard(
    val boardRange: GameBoardRange,
    val mineQuantity: Int
) {

    init {
        require(boardRange.calculateArea() >= mineQuantity) { "지뢰 개수는 높이 * 너비를 초과할 수 없습니다." }
    }
}
