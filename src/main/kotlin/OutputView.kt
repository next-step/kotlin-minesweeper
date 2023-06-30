object OutputView {
    private const val MINE = "*"
    private const val TILE = "C"

    fun printGameStart(map: GameMap) {
        println("지뢰찾기 게임 시작")

        map.field.forEach { printColumn(it) }
    }

    private fun printColumn(column: List<Tile>) {
        column.forEach { printTile(it) }
        println()
    }

    private fun printTile(tile: Tile) {
        val output = when (tile) {
            is Mine -> MINE
            else -> TILE
        }
        print("$output ")
    }
}
