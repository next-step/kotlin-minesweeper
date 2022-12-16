package minesweeper.domain

class Map(val maxSize: Position, val mines: List<Mine>) {
    companion object {
        const val MINE_STRING = "*"
        const val SAFE_STRING = "C"
    }
}
