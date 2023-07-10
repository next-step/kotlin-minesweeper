package domain

class MineSweeperBoard(boardSize: BoardSize) {
    private val _board = Array(boardSize.height) { Array(boardSize.width) { Cell.createNormalCell() } }

    val width: Int
        get() = _board[0].size

    val height: Int
        get() = _board.size

    fun putMines(vararg position: Position) {
        position.forEach {
            putMine(it)
        }
    }

    fun putMine(position: Position) {
        _board[position.y][position.x] = Cell.createMineCell()
    }

    fun isMine(position: Position): Boolean {
        return _board[position.y][position.x] is Cell.MineCell
    }

    fun getCell(position: Position): Cell {
        return _board[position.y][position.x]
    }

    fun getRow(y: Int): List<Cell> {
        return _board[y].toList()
    }

    fun getMineCountAround(position: Position): Int {
        return getAroundPositions(position).count { isMine(it) }
    }

    private fun getValidPosition(x: Int, y: Int): Position? {
        if (x in 0 until width && y in 0 until height) {
            return Position(x, y)
        }
        return null
    }

    private fun getAroundPositions(position: Position): List<Position> {
        return Direction.values().mapNotNull { direction ->
            getValidPosition(position.x + direction.dx, position.y + direction.dy)
        }
    }

    fun allPositions(): List<Position> {
        return (0 until height).flatMap { y ->
            (0 until width).map { x ->
                Position(x, y)
            }
        }
    }
}

enum class Direction(val dx: Int, val dy: Int) {
    NORTH(0, -1),
    NORTHEAST(1, -1),
    EAST(1, 0),
    SOUTHEAST(1, 1),
    SOUTH(0, 1),
    SOUTHWEST(-1, 1),
    WEST(-1, 0),
    NORTHWEST(-1, -1)
}
