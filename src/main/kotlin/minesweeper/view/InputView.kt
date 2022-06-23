package minesweeper.view

import minesweeper.domain.board.dto.MineBoardRequest

object InputView {

    fun inputMineBoardRequest(): MineBoardRequest {
        println("높이를 입력하세요.")
        val height = requireNotNull(readlnOrNull()?.toIntOrNull())
        println("너비를 입력하세요.")
        val width = requireNotNull(readlnOrNull()?.toIntOrNull())
        println("지뢰는 몇 개인가요?")
        val numberOfMines = requireNotNull(readlnOrNull()?.toIntOrNull())

        return MineBoardRequest(width, height, numberOfMines)
    }

    fun inputPositionToOpenCell(): List<Int> {
        print("open: ")
        val (x, y) = requireNotNull(readlnOrNull()?.split(","))
        return listOf(x.int(), y.int())
    }

    private fun String.int() = this.trim().toInt()
}
