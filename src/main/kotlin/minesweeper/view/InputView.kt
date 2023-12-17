package minesweeper.view

import minesweeper.domain.model.Board
import minesweeper.domain.model.Height
import minesweeper.domain.model.MineCount
import minesweeper.domain.model.Width

object InputView {

    fun getBoard(): Board {
        val height = getHeight()
        val width = getWidth()
        val mineCount = getMineCount()
        return Board.create(width, height, mineCount)
    }

    private fun getHeight(): Height {
        println("높이를 입력하세요.")
        val height = readln()
        println()

        require(height.toIntOrNull() != null) {
            "높이는 숫자여야 합니다."
        }

        return Height.from(height.toInt())
    }

    private fun getWidth(): Width {
        println("넓이를 입력하세요.")
        val width = readln()
        println()

        require(width.toIntOrNull() != null) {
            "넓이는 숫자여야 합니다."
        }

        return Width.from(width.toInt())
    }

    private fun getMineCount(): MineCount {
        println("지뢰는 몇 개인가요?")
        val mineCount = readln()
        println()

        require(mineCount.toIntOrNull() != null) {
            "지뢰 수는 숫자여야 합니다."
        }

        return MineCount.from(mineCount.toInt())
    }
}
