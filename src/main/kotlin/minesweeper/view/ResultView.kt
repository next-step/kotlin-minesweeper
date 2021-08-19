package minesweeper.view

object ResultView {

    private const val START_ROW = 0

    fun printAllMineGround(markersWithMine: MutableList<List<String>>) {
        (markersWithMine.indices).map { x ->
            printMineGround(markersWithMine, x)
            println()
        }
    }

    private fun printMineGround(markersWithMine: MutableList<List<String>>, column: Int) {
        (markersWithMine[START_ROW].indices).map { row ->
            print(markersWithMine[column][row])
        }
    }
}