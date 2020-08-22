package model

class Gamer(private val board: Board) {
    private val _gameBoard = mutableMapOf<Coordinates, MineType>()
    val gameBoard: Map<Coordinates, MineType> get() = _gameBoard

    init {
        _gameBoard.putAll(board.getInitBoard())
    }

    fun clickCoordinate(coordinates: Coordinates) {
        val showedArea = board.getShowedArea(coordinates)
        showedArea.map {
            _gameBoard[it.key] = it.value
        }
    }
}
