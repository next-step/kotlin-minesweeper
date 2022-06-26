package ui.output

import ui.output.dto.Line
import ui.output.dto.MatrixDto

object OutputUI {

    fun showBoard(matrixDto: MatrixDto): Unit = with(StringBuilder()) {
        appendLine("지뢰찾기 게임 시작")
        matrixDto.lines.forEach {
            appendLine(buildLine(it))
        }
        print(this)
    }

    private fun buildLine(line: Line) = buildString {
        line.displayedCells.forEach {
            append("${it.displayView} ")
        }
    }
}
