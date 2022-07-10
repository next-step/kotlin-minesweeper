package minesweeper.entity

data class MapInformation(val height: Int, val width: Int, val numberOfMines: Int) {
    init {
        require(height > 0) { "높이가 양수여야합니다" }
        require(width > 0) { "너비가 양수여야합니다" }
        require(numberOfMines > 0) { "지뢰의 갯수가 양수여야합니다" }
    }
}
