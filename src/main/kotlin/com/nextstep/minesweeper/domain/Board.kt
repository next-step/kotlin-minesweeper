package com.nextstep.minesweeper.domain

import com.nextstep.minesweeper.domain.Icon.BLANK
import com.nextstep.minesweeper.domain.Icon.MINE
import kotlin.random.Random

class Board(
    private val height: Int,
    private val width: Int,
    private val numberOfMines: Int
) {
    private val _cells: Array<Array<Icon>>
    val cells: Array<Array<Icon>>
        get() = _cells.clone()

    init {
        require(height > 1 && width > 1) { "높이와 너비는 1보다 커야합니다. height: $height, width: $width" }
        require(numberOfMines >= 1 && numberOfMines < height * width) {
            "지뢰의 수는 1보다 크거나 같고, 입력한 보드의 크기보다 작아야합니다. numberOfMines: $numberOfMines"
        }

        _cells = Array(height) { Array(width) { BLANK } }
        placeMines()
    }

    private fun placeMines() {
        var count = 0
        while (count < numberOfMines) {
            val y = Random.nextInt(height)
            val x = Random.nextInt(width)
            if (_cells[x][y] == BLANK) {
                _cells[x][y] = MINE
                count++
            }
        }
    }
}
