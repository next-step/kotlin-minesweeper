package minesweeper.dto

data class GameBoardRequest(val height: Int, val width: Int, val minesNumber: Int) {
    init {
        require(height * width >= minesNumber) { "지뢰의 갯수는 전체 게임판 보다 크면 안됩니다." }
    }
}
