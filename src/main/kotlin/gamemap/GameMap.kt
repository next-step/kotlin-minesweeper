package gamemap

data class GameMap(
    private val gameMap: List<List<Cell>>
) {
    init {
        require(gameMap.isNotEmpty()) { "invalid game map height" }
        require(gameMap.first().isNotEmpty()) { "invalid game map width" }
        require(gameMap.flatten().any { cell -> cell.isMine }) { "game map should have at least 1 mine cell" }
    }
    fun cellAt(r: Int, c: Int) = gameMap[r][c]

    val mineCount = gameMap.flatten().count { cell -> cell.isMine }

    val width = gameMap.first().size

    val height = gameMap.size
}
