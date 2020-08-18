package model

class Board(boardSize: BoardSize, mineIndexes: List<Int>) {
    private val _linear = mutableListOf<Char>()
    val grid: List<List<Char>>

    init {
        setupLinear(boardSize, mineIndexes)
        grid = convertToGrid(boardSize)
    }

    fun getMineCoordinates(): List<Coordinates> {
        val mineCoordinates = mutableListOf<Coordinates>()
        for (row in grid.indices) {
            makeMineCoordinates(row, mineCoordinates)
        }
        return mineCoordinates
    }

    private fun makeMineCoordinates(row: Int, mineCoordinates: MutableList<Coordinates>) {
        for (col in grid.indices) {
            if (grid[row][col] != MineType.MINE.symbol) continue
            mineCoordinates.add(Coordinates(row, col))
        }
    }

    private fun convertToGrid(boardSize: BoardSize) = _linear.chunked(boardSize.getRow())

    private fun setupLinear(boardSize: BoardSize, mineIndexes: List<Int>) {
        repeat(boardSize.get()) {
            _linear.add(MineType.NONE.symbol)
        }
        mineIndexes.forEach {
            _linear[it] = MineType.MINE.symbol
        }
    }
}
