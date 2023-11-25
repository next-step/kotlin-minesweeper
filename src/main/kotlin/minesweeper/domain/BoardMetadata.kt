package minesweeper.domain

data class BoardMetadata(val height: Int, val width: Int, val numOfMine: Int) {

    init {
        require(height > 0) { "높이는 0보다 큰 양수만 허용합니다." }
        require(width > 0) { "넓이는 0보다 큰 양수만 허용합니다." }
        require(numOfMine > 0) { "지뢰의 개수는 0보다 큰 양수만 허용합니다." }
        require(height * width > numOfMine) { "지뢰의 개수는 전체 칸의 개수보다 작아야 합니다." }
    }
}
