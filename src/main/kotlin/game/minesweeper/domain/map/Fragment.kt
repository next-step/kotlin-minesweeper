package game.minesweeper.domain.map

data class Fragment(
    private val coordinate: Coordinate,
    private var _hasMine: Boolean = false,
    private var borderMine: Int = 0,
) {

    fun setMine() {
        _hasMine = true
    }

    fun hasMine() = _hasMine

    fun included(coordinates: List<Coordinate>) = coordinates.contains(coordinate)


    companion object {
        fun of(x: Int, y: Int) = Fragment(Coordinate(x, y))
    }
}
