package minesweeper.view

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printLineFeed() {
        println()
    }

    fun printLand(tiles: List<List<String>>) {
        for (row in tiles) {
            printRow(row)
        }
    }

    private fun printRow(row: List<String>) {
        for (tile in row) {
            print("$tile ")
        }
        println()
    }
}
