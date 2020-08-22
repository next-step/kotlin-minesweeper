package model

class Gamer(private val board: Board) {
    private val _gameBoard = mutableMapOf<Coordinates, MineType>()
    val gameBoard: Map<Coordinates, MineType> get() = _gameBoard

    init {
        _gameBoard.putAll(board.getInitBoard(MineType.NONE))
    }

    fun clickCoordinate(coordinates: Coordinates) {
        board.getShowedArea(coordinates).forEach { _gameBoard[it.key] = it.value }
    }

    fun getCurrentGameBoard(): List<List<Char>> {
        val row = _gameBoard.keys.groupBy { it.row }.size
        return _gameBoard.values
            .chunked(row)
            .map { rowElements -> rowElements.map { it.ascii.toChar() } }
    }
}
