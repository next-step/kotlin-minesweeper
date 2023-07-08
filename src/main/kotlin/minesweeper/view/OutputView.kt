package minesweeper.view

import minesweeper.domain.CellInfo
import minesweeper.domain.CellInfos
import minesweeper.domain.MineBoardStatus

private const val MINE_BOARD_ROW_DELIMITER = "\n"
private const val MINE_BOARD_COLUMN_DELIMITER = " "

fun printMineBoardView(cellInfos: CellInfos) {
    println(parseMineBoardView(cellInfos.values, cellInfos.height))
}

fun printMineGameResult(mineBoardStatus: MineBoardStatus) {
    println(GameResultView.from(mineBoardStatus))
}

private fun parseMineBoardView(cellInfos: List<CellInfo>, height: Int): String =
    (0 until height).joinToString(MINE_BOARD_ROW_DELIMITER) { parseToPrintView(cellInfos.getSortedCellInfoByHeight(it)) }

private fun List<CellInfo>.getSortedCellInfoByHeight(height: Int): List<CellInfo> = this.filter { it.row == height }
    .sortedBy { it.column }

private fun parseToPrintView(cellInfos: List<CellInfo>): String =
    cellInfos.joinToString(MINE_BOARD_COLUMN_DELIMITER) { CellView.from(it.cellType).printView }
