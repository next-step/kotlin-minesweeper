package view

import domain.Minesweeper
import domain.Place

class ResultView {

    fun printMinesweeperBoard(minesweeper: Minesweeper) {
        var result = ""
        for (row in minesweeper.board) {
            result += row.joinToString(" ") { place -> place.toTransform() }
            result += "\n"
        }

        println(result)
    }

    private fun Place.toTransform(): String {
        return placeType.transform
    }
}
