package minsweeper.view

import minsweeper.domain.Cell
import minsweeper.domain.Coordinate

object ResultView {

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printEnterRightCoordinate(coordinate: Coordinate) {
        println("[${coordinate.row}, ${coordinate.column}] 셀은 존재하지 않습니다. ")
    }

    fun printLoseGame() {
        println("Lose Game.")
    }

    fun printCells(cells: Map<Coordinate, Cell>) {
        var rowCount = 1
        val comparator = compareBy<Coordinate> { it.row }.thenBy { it.column }
        cells.toSortedMap(comparator)
            .forEach { (coordinate, cell) ->
                if (coordinate.row > rowCount) {
                    println()
                    rowCount++
                }
                print("${cell.print()} ")
            }

        println()
    }

    private fun Cell.print(): String {
        if (!this.isOpened) {
            return "C"
        }

        return when (this) {
            is Cell.Island -> this.aroundMineAmount.toString()
            is Cell.Mine -> "*"
        }
    }

}