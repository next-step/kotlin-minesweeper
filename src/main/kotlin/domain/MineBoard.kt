package domain

class MineBoard (private val width: Int, height: Int) {
    val boards: Array<Array<Coordinate>>

    init {
        boards = Array(height) { y -> Array(width) { x -> Brick(x, y) } }
    }

    fun setMines(mines: List<Mine>) {
        for(mine in mines) {
            setMine(mine)
        }
    }

    private fun setMine(mine: Mine) {
        boards[mine.x][mine.y] = mine
    }
}
