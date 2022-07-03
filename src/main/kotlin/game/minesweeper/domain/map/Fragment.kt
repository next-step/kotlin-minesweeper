package game.minesweeper.domain.map

data class Fragment(private val coordinate: Coordinate, private var _hasMine: Boolean = false) {

    fun setMine() {
        _hasMine = true
    }

    fun hasMine() = _hasMine

    companion object {
        fun of(x: Int, y: Int) = Fragment(Coordinate(x, y))
    }
}
