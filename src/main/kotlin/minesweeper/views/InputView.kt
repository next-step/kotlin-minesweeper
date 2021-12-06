package minesweeper.views

import minesweeper.domain.block.Position

object InputView {

    tailrec fun askHeight(): Int {
        println(HEIGHT_QUESTION)
        return readLine()?.toInt() ?: askHeight()
    }

    tailrec fun askWidht(): Int {
        println(WIDTH_QUESTION)
        return readLine()?.toInt() ?: askWidht()
    }

    tailrec fun askMine(): Int {
        println(MINE_QUESTION)
        return readLine()?.toInt() ?: askWidht()
    }

    tailrec fun askTarget(): Position {
        println()
        print(STATE_OPEN)
        val target = readLine()!!.split(DELIMITER).map { it.trim().toInt() }
        if (target.size != POSITION_SIZE) {
            askTarget()
        }
        return Position(target[X_POSITION], target[Y_POSITION])
    }

    private const val STATE_OPEN = "open: "
    private const val HEIGHT_QUESTION = "높이를 입력하세요."
    private const val WIDTH_QUESTION = "너비를 입력하세요."
    private const val MINE_QUESTION = "지뢰는 몇 개인가요?"
    private const val DELIMITER = ","
    private const val POSITION_SIZE = 2
    private const val X_POSITION = 0
    private const val Y_POSITION = 1
}
