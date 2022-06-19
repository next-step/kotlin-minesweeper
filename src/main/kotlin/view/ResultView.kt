package view

import domain.Minesweeper

class ResultView {

    fun printMinesweeperBoard(minesweeper: Minesweeper) {
        var result = ""
        for (row in minesweeper.board) {
            result += row.joinToString(" ")
            result += "\n"
        }

        println(result)
    }
}
