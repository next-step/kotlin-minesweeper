package view

import domain.Minesweeper
import domain.Place
import domain.PlaceType

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
        return when (this.placeType) {
            PlaceType.MINE -> "*"
            PlaceType.NOT_MINE -> this.nearMineCount.toString()
        }
    }
}
