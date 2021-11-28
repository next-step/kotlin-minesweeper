package minesweeper.view

object OutputView {

    fun printStart(board: BoardDto) {
        println("지뢰찾기 게임 시작")

        board.rows.forEach {
            printRow(it)
        }
    }

    private fun printRow(row: RowDto) {
        row.cells.forEach {
            print("${it.toView()} ")
        }
        println()
    }

    private fun CellDto.toView(): String {
        return if (mine) {
            "*"
        } else {
            "C"
        }
    }
}
