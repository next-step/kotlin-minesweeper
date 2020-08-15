package minesweeper.domain

class MineNumber(val mineNumber: Int, private val boardSize: BoardSize) {
    init {
        val totalCount = boardSize.height.length * boardSize.width.length
        require(mineNumber in 0..totalCount) { "지뢰는 0과 $totalCount 사이의 숫자를 입력해주세요." }
    }
}
