package step4.view

import step4.domain.CellInfo
import step4.domain.CellInfos
import step4.domain.state.MinesweeperState

private const val ROW_DELIMITER = "\n"
private const val COLUMN_DELIMITER = " "

fun printStartGame() {
    println("지뢰찾기 게임 시작")
}

fun printCurrentStatus(cellInfos: CellInfos) {
    println(parseToPrintView(cellInfos.height, cellInfos.values))
}

fun printGameResult(gameState: MinesweeperState) {
    println(GameResultView.from(gameState).view)
}

private fun parseToPrintView(height: Int, cellInfos: List<CellInfo>): String =
    (0 until height).joinToString(ROW_DELIMITER) { parseToRowPrintView(cellInfos.findCellInfosByRow(it)) }

private fun List<CellInfo>.findCellInfosByRow(row: Int): List<CellInfo> = this.filter { row == it.row }
    .sortedBy { it.column }

private fun parseToRowPrintView(cellInfos: List<CellInfo>): String =
    cellInfos.joinToString(COLUMN_DELIMITER) { CellView.from(it.cellType).view }
