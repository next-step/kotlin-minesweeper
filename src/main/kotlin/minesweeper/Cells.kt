package minesweeper

class Cells(val cells: List<Cell>, val width: Int, val height: Int) {

    init {
        require(width > 0 && height > 0) { "너비와 높이는 0보다 커야합니다" }
        require(cells.size == width * height) { "셀의 크기는 너비와 높이를 곱한 값이어야 합니다" }
    }
}
