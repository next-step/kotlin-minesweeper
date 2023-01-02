package minesweeper.domain

class Game(private val board: MineBoard) {

    fun start(targetPositionText: String) {
        val position: Position = Position.toPosition(board.rowSize(), board.columnSize(), targetPositionText)
        open(position.index)
    }

    private fun open(targetPosition: Int) {
        val target: Coordinate = board.coordinates[targetPosition]
        if (target.isOpen()) return

        target.open()

        if (target.isMine()) return
        if (target.count > 0) return

        val position = target.position
        if (position.topLeft >= 0) open(position.topLeft)
        if (position.top >= 0) open(position.top)
        if (position.topRight >= 0) open(position.topRight)
        if (position.right >= 0) open(position.right)
        if (position.bottomRight >= 0) open(position.bottomRight)
        if (position.bottom >= 0) open(position.bottom)
        if (position.bottomLeft >= 0) open(position.bottomLeft)
        if (position.left >= 0) open(position.left)
    }
}
