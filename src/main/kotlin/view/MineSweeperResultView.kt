package view

import domain.cell.Cell
import domain.map.MapCapture

class MineSweeperResultView {

    fun displayStartMineSweeperGameMessage() {
        println("지뢰 찾기 게임 시작")
    }

    fun display(mapCapture: MapCapture) {
        val text = buildString {
            appendLine()
            appendLine("지뢰찾기 게임 시작")
            appendLine(mapCapture.cells.makeMapText())
        }
        println(text)
    }

    private fun List<List<Cell>>.makeMapText(): String {
        return joinToString("\n") { row -> row.makeRowText() }
    }

    private fun List<Cell>.makeRowText(): String {
        return joinToString(" ") { cell -> cell.makeDisplayText() }
    }

    private fun Cell.makeDisplayText(): String {
        if (openState.isHide()) {
            return "C"
        }
        return when (this) {
            is Cell.Mine -> "*"
            is Cell.Ground -> aroundMineCount.value.toString()
        }
    }
}
