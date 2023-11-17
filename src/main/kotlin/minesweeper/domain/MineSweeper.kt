package minesweeper.domain

class MineSweeper(val mapPositions: List<Position>, val mines: Mines) {
    init {
        require(mapPositions.size >= mines.count()) { ERROR_MESSAGE }
    }

    companion object {
        private const val ERROR_MESSAGE = "맵보다 지뢰가 많습니다."
    }
}
