package gamemap

data class GameMap(
    private val _gameMap: List<List<Cell>>
) {
    init {
        require(_gameMap.isNotEmpty()) { "invalid game map height" }
        require(_gameMap.first().isNotEmpty()) { "invalid game map width" }
        require(_gameMap.flatten().any { cell -> cell.isMine }) { "game map should have at least 1 mine cell" }
    }
    fun cellAt(r: Int, c: Int) = _gameMap[r][c]

    fun mineCount() = _gameMap.flatten().count { cell -> cell.isMine }

    val width = _gameMap.first().size

    val height = _gameMap.size
}
