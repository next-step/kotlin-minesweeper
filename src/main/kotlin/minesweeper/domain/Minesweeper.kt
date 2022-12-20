package minesweeper.domain

import minesweeper.dto.NumberOfMinesResult

class Minesweeper(private var minefield: Minefield) {

    fun enrollMines(numberOfMinesResult: NumberOfMinesResult): Minefield {
        return minefield.enrollMines(numberOfMinesResult)
    }
}
