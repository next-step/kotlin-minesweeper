package minesweeper.view

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printLineFeed() {
        println()
    }

    fun printLand(width: Int, tiles: List<String>) {
        val land = tiles.joinToString("") { tile -> "$tile " }
        val landWithLineFeed = land.chunked(width * 2)
        landWithLineFeed.forEach { tile -> println(tile) }
    }
}
