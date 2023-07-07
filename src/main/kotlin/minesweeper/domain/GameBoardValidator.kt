package minesweeper.domain

object GameBoardValidator {
    fun validateGameRequest(
        height: Int,
        width: Int,
        minesNumber: Int
    ) {
        require(height > 0 && width > 0) { "높이와 너비는 양의 정수값만 가능합니다." }
        require(minesNumber >= 0) { "지뢰의 갯수는 음수가 되면 안됩니다." }
        require(height * width >= minesNumber) { "지뢰의 갯수는 전체 게임판 보다 크면 안됩니다." }
    }
}
