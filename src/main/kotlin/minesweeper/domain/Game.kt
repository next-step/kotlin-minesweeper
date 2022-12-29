package minesweeper.domain

class Game(private val openPosition: Int, private val board: MineBoard) {

    var isLose: Boolean = false
        private set

    fun start() {
        val result = open(board.coordinates, openPosition)

    }

    private fun open(coordinates: Coordinates, index: Int) {
        val coordinate = coordinates[index]
        if (coordinate.isMine()) return
        if (coordinate.count > 0) return

        val position = coordinate.position
        position.topLeft > 0
    }
}
