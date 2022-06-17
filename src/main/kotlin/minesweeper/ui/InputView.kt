package minesweeper.ui

import minesweeper.domain.vo.Height
import minesweeper.domain.vo.NumberOfMine
import minesweeper.domain.vo.Width

object InputView {
    fun inputHeight(): Height {
        println("높이를 입력하세요.")
        return readln().toIntOrNull()?.let(::Height) ?: throw IllegalArgumentException("높이는 숫자만 입력 가능합니다.")
    }

    fun inputWidth(): Width {
        println("너비를 입력하세요.")
        return readln().toIntOrNull()?.let(::Width) ?: throw IllegalArgumentException("너비는 숫자만 입력 가능합니다.")
    }

    fun inputNumberOfMine(): NumberOfMine {
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull()?.let(::NumberOfMine)
            ?: throw IllegalArgumentException("지뢰개수는 숫자만 입력 가능합니다.")
    }
}
