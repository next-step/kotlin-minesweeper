package step4.view

import step4.domain.CellInfo
import step4.domain.CellInfos

fun printStartGame() {
    println("지뢰찾기 게임 시작")
}

fun printCurrentStatus(cellInfos: CellInfos) {
    println(parseToPrintView(cellInfos.height, cellInfos.values))
}

private fun parseToPrintView(height: Int, cellInfos: List<CellInfo>): String =
    (0 until height).joinToString("\n") { parseToRowPrintView(cellInfos.findCellInfosByRow(it)) }

private fun List<CellInfo>.findCellInfosByRow(row: Int): List<CellInfo> = this.filter { row == it.row }
    .sortedBy { it.column }

private fun parseToRowPrintView(cellInfos: List<CellInfo>): String =
    cellInfos.joinToString(" ") { CellView.from(it.cellType).printView }
