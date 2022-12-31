package com.nextstep.minesweeper.domain

class Board(height: Int, width: Int, numberOfMines: Int) {
    private val cells: Array<IntArray>

    init {
        require(height > 1 && width > 1) { "높이와 너비는 1보다 커야합니다. height: $height, width: $width" }
        require(numberOfMines >= 1 && numberOfMines < height * width) {
            "지뢰의 수는 1보다 크거나 같고, 입력한 보드의 크기보다 작아야합니다. numberOfMines: $numberOfMines"
        }

        cells = Array(height) { IntArray(width) }
    }
}
