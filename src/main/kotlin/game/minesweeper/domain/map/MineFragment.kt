package game.minesweeper.domain.map

class MineFragment(
    coordinate: Coordinate,
    borderMine: Int = 0,
) : Fragment(coordinate, borderMine) {
    override fun hasMine(): Boolean = true
}
