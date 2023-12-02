package minesweeper

object ResultView {
    fun start() {
        println("지뢰찾기 게임 시작")
    }

    fun showMap(mineSweeper: MineSweeper) {
        for (row in 1..mineSweeper.mineMap.mapInfo.rowNumber) {
            printRow(mineSweeper, row)
        }
    }

    private fun printRow(
        mineSweeper: MineSweeper,
        row: Int
    ) {
        for (col in 1..mineSweeper.mineMap.mapInfo.columnNumber) {
            val point = Point(row, col)
            if (!mineSweeper.clickedSet.contains(point)) {
                print("C ")
                continue
            }
            when (val info = mineSweeper.mineMap.mineMap[point]) {
                is MapTile.Mine -> print("* ")
                is MapTile.Blank -> {
                    print("${info.nearCount} ")
                }

                else -> print("0 ")
            }
        }
        println()
    }

    fun showAlreadyClickedMessage() {
        println("This point is already clicked. Choose other point.")
    }

    fun showGameOver() {
        println("Lose Game.")
    }

    fun showError() {
        println("Error.")
    }

    fun showFinished() {
        println("Congratulation! You win the game!")
    }
}
