package minesweeper.domain

class GameBoardValidator {
    fun validateGameRequest(
        height: Int,
        width: Int,
        minesNumber: Int
    ) {
        require(height > 0 && width > 0 && minesNumber > 0) {"각 숫자는 음수가 되면 안됩니다."}
        require(height * width >= minesNumber) { "지뢰의 갯수는 전체 게임판 보다 크면 안됩니다." }
    }
}
