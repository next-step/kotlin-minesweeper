package com.nextstep.minesweeper.domain

import kotlin.random.Random

class Board(
    private val height: Int,
    private val width: Int,
    private val numberOfMines: Int
) {
    private val _cells: Array<IntArray>
    val cells: Array<IntArray>
        get() = _cells.clone()

    init {
        require(height > 1 && width > 1) { "높이와 너비는 1보다 커야합니다. height: $height, width: $width" }
        require(numberOfMines >= 1 && numberOfMines < height * width) {
            "지뢰의 수는 1보다 크거나 같고, 입력한 보드의 크기보다 작아야합니다. numberOfMines: $numberOfMines"
        }

        _cells = Array(height) { IntArray(width) }
        placeMines()
    }

    private fun placeMines() {
        var count = 0
        while (count < numberOfMines) {
            val y = Random.nextInt(height)
            val x = Random.nextInt(width)
            if (_cells[x][y] == 0) {
                _cells[x][y] = 1
                count++
            }
        }
    }
}
