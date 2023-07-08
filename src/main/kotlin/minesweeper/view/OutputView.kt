package minesweeper.view

import minesweeper.domain.CellInfo
import minesweeper.domain.CellInfos

private const val MINE_BOARD_ROW_DELIMITER = "\n"
private const val MINE_BOARD_COLUMN_DELIMITER = " "

fun printMineBoardView(cellInfos: CellInfos) {
    println("지뢰찾기 게임 시작")
    println(parseMineBoardView(cellInfos.values, cellInfos.height))
}

private fun parseMineBoardView(cellInfos: List<CellInfo>, height: Int): String =
    (0 until height).joinToString(MINE_BOARD_ROW_DELIMITER) { parseToPrintView(cellInfos.getSortedCellInfoByHeight(it)) }

private fun List<CellInfo>.getSortedCellInfoByHeight(height: Int): List<CellInfo> = this.filter { it.row == height }
    .sortedBy { it.column }

private fun parseToPrintView(cellInfos: List<CellInfo>): String =
    cellInfos.joinToString(MINE_BOARD_COLUMN_DELIMITER) { CellView.from(it.cellType).printView }
