package view

import Minesweeper
import Position

class OutputView {
    fun showMinesweeper(minesweeper: Minesweeper) {
        println("지뢰찾기 게임 시작")
        val stringBuilder = StringBuilder()
        val size = minesweeper.size
        for (i in 0 until size.height) {
            for (j in 0 until size.width) {
                val position = Position(j, i)
                val mineType = minesweeper.getMineType(position = position)
                stringBuilder.append(mineType.displayText)
                if (j == size.width - 1) {
                    stringBuilder.append("\n")
                } else {
                    stringBuilder.append(" ")
                }
            }
        }
        println(stringBuilder)
    }
}
