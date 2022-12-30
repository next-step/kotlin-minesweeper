package view

import domain.BoardInfo
import domain.Column
import dto.BoardDto

object ResultView {
    fun printBoard(boardInfo: BoardInfo, boardDto: BoardDto) {
        println("지뢰찾기 게임 시작")

        boardDto.cells.forEachIndexed { index, it ->
            print("$it ")

            if (isSpace(index, boardInfo.column)) println()
        }
    }

    private fun isSpace(index: Int, column: Column): Boolean {
        return (index + 1) % column.value == 0
    }
}
