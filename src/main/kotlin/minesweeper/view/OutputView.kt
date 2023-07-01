package minesweeper.view

import minesweeper.domain.CellInfos

fun printMineBoardView(cellInfos: CellInfos) {
    println("지뢰찾기 게임 시작")

    for (i in 0 until cellInfos.height) {
        for (j in 0 until cellInfos.width) {
            val cellView = CellView.from(cellInfos.cellInfos.first { it.row == i && it.column == j }.cellType).printView
            print("$cellView ")
        }
        println()
    }
}
