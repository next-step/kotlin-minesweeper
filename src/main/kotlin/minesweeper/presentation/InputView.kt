package minesweeper.presentation

import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width

object InputView {
    fun inputWidth(): Width {
        println(STRING_INPUT_COLUMN)
        return Width(readln())
    }

    fun inputHeight(): Height {
        println(STRING_INPUT_ROW)
        return Height(readln())
    }

    fun inputMineCount(
        width: Width,
        height: Height,
    ): MineCount {
        println(STRING_INPUT_MINE_COUNT)
        return MineCount(readln(), width, height)
    }

    private const val STRING_INPUT_COLUMN = "길이를 입력하세요."
    private const val STRING_INPUT_ROW = "높이를 입력하세요."
    private const val STRING_INPUT_MINE_COUNT = "지뢰는 몇 개인가요?"
}
