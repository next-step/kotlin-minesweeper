package game.minesweeper.domain.map

open class Fragment(
    private val coordinate: Coordinate,
    private var borderMine: Int = 0,
) {

    fun included(coordinates: List<Coordinate>) = coordinates.contains(coordinate)

    fun count(coordinates: List<Coordinate>) = coordinates.count { it == coordinate }

    fun increaseBorderMine(count: Int) {
        borderMine += count
    }

    fun borderMine() = borderMine

    fun convertToMine(): MineFragment {
        return MineFragment(coordinate, borderMine)
    }

    open fun hasMine() = false

    companion object {
        fun of(x: Int, y: Int) = Fragment(Coordinate(x, y))
    }
}
