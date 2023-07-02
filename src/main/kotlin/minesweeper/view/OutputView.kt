package minesweeper.view

import minesweeper.domain.CellInfos

private const val MINE_BOARD_ROW_DELIMITER = "\n"
private const val MINE_BOARD_COLUMN_DELIMITER = " "

fun printMineBoardView(cellInfos: CellInfos) {
    println("지뢰찾기 게임 시작")
    println(parseMineBoardView(cellInfos))
}

private fun parseMineBoardView(cellInfos: CellInfos) =
    (0 until cellInfos.height).joinToString(MINE_BOARD_ROW_DELIMITER) { i -> parseRowView(cellInfos, i) }

private fun parseRowView(cellInfos: CellInfos, i: Int) =
    (0 until cellInfos.width).joinToString(MINE_BOARD_COLUMN_DELIMITER) { j -> parseToCellPrintView(cellInfos, i, j) }

private fun parseToCellPrintView(cellInfos: CellInfos, i: Int, j: Int) =
    CellView.from(cellInfos.cellInfos.first { it.row == i && it.column == j }.cellType).printView
