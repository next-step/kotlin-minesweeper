package domain

class MineSweeperBoard(boardSize: BoardSize, private val minePositions: List<Position>) {
    private val _board = Array(boardSize.height) { Array(boardSize.width) { Cell.createNormalCell() } }

    init {
        setMines()
        setCountAroundMines()
    }

    private fun setMines() {
        minePositions.map {
            putMines(it)
        }
    }

    private fun setCountAroundMines() {
        allPositions().map { position ->
            if (!isMine(position)) {
                val count = getMineCountAround(position)
                getCell(position).countOfMinesAround = count
            }
        }
    }

    val width: Int
        get() = _board[0].size

    val height: Int
        get() = _board.size

    private fun putMines(vararg position: Position) {
        position.forEach {
            putMine(it)
        }
    }

    private fun putMine(position: Position) {
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

    private fun allPositions(): List<Position> {
        return (0 until height).flatMap { y ->
            (0 until width).map { x ->
                Position(x, y)
            }
        }
    }
}

interface MinePositionsGenerator {
    fun generate(): List<Position>
}
