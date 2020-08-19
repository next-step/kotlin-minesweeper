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

    fun convertToMineCount(): List<List<Char>> {
        val mineMap = Array(grid.size) { IntArray(grid[0].size) { ZERO_ASCII } }
        val mineCoordinates = getMineCoordinates()
        setupMineAround(mineCoordinates, mineMap)
        setupMine(mineCoordinates, mineMap)
        return mineMap.map { row -> row.map { it.toChar() } }
    }

    private fun setupMine(mineCoordinates: List<Coordinates>, mineMap: Array<IntArray>) {
        mineCoordinates.forEach { mineMap[it.row][it.col] = MineType.MINE.ascii }
    }

    private fun setupMineAround(mineCoordinates: List<Coordinates>, mineMap: Array<IntArray>) {
        mineCoordinates.forEach { mineCoordinate ->
            val aroundMine = mineCoordinate.getAround(grid.lastIndex, grid[0].lastIndex)
            aroundMine.map { mineMap[it.row][it.col] += 1 }
        }
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

    companion object {
        private const val ZERO_ASCII = 48
    }
}
