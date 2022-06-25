package ui.output

import domain.Cell
import ui.output.dto.BoardDto
import ui.output.dto.Line

object OutputUI {

    fun showBoard(boardDto: BoardDto) {
        println("지뢰찾기 게임 시작")
        boardDto.lines.forEach {
            printLine(it)
            println()
        }
    }

    private fun printLine(line: Line) {
        line.cells.forEach {
            print(it.display())
        }
    }

    private fun Cell.display(): String {
        return when (this) {
            is Cell.Mine -> "*"
            is Cell.Safe -> "C"
        }
    }
}
