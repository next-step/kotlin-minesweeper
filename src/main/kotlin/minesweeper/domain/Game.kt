package minesweeper.domain

class Game(private val board: MineBoard) {

    fun start(targetPositionText: String): Boolean {
        val index: Int = Position.toIndex(board.columnSize(), targetPositionText)
        val target: Coordinate = board.coordinates[index]
        if (target.isMine()) return false
        open(board.rowSize(), board.columnSize(), index)
        return true
    }

    private fun open(rows: Int, columns: Int, index: Int) {
        val target: Coordinate = board.coordinates[index]
        if (target.isOpen()) return

        target.open()

        if (target.isMine()) return
        if (target.count > 0) return

        val positionArray = Position.values()
        repeat(positionArray.size) {
            val position = positionArray[it]
            val nextIndex = position.calculate(rows, columns, index)
            if (nextIndex >= 0) open(rows, columns, nextIndex)
        }
    }
}
