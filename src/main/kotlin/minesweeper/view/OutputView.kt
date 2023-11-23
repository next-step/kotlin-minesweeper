package minesweeper.view

object OutputView {
    fun printMap(mineMap: Array<Array<String>>) {
        println("\n지뢰찾기 게임 시작")
        for (row in mineMap) {
            printCols(row)
        }
    }

    private fun printCols(row: Array<String>) {
        for (it in row) {
            print("$it ")
        }
        println()
    }
}
