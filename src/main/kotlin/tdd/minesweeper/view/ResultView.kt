package tdd.minesweeper.view

import tdd.minesweeper.domain.Cell
import tdd.minesweeper.domain.Land

object ResultView {
    fun printGameStart() {
        println("지뢰찾기 게임을 시작합니다.")
    }

    fun printGrid(grid: List<List<Cell>>) {
        grid.forEach { row -> printRow(row) }
    }

    private fun printRow(row: List<Cell>) {
        row.forEach { printCell(it) }
        println()
    }

    private fun printCell(cell: Cell) {
        when {
            !cell.isOpened() -> print("C ")
            cell is Land -> print("${cell.adjacentMineCount} ")
            else -> print("X ")
        }
    }
}
