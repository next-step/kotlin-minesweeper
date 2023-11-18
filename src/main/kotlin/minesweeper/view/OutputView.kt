package minesweeper.view

object OutputView {

    fun printMineSweeperStart() {
        println(MINESWEEPER_START_MESSAGE)
    }
    fun printMineSweeper(chunked: Int, result: List<Pair<Int, Boolean>>) {
        result.chunked(chunked).forEach { it.printMineSweeperRow() }
    }

    private fun List<Pair<Int, Boolean>>.printMineSweeperRow() {
        val convert = this.map { if (it.second) MINE else it.first.toString() }
        println(convert.joinToString(" "))
    }

    private const val MINESWEEPER_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE = "*"
}
