package game.minesweeper.domain.map

data class Fragment(
    private val coordinate: Coordinate,
    private var borderMine: Int = 0,
    var hasMine: Boolean = false,
) {

    fun setMine() {
        hasMine = true
    }

    fun included(coordinates: List<Coordinate>) = coordinates.contains(coordinate)

    fun count(coordinates: List<Coordinate>) = coordinates.count { it == coordinate }

    fun increaseBorderMine(count: Int) {
        borderMine += count
    }

    fun borderMine() = borderMine

    companion object {
        fun of(x: Int, y: Int) = Fragment(Coordinate(x, y))
    }
}
