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
        mineCoordinates.forEach { makeMineMap(mineMap, it) }
        mineCoordinates.forEach { mineMap[it.row][it.col] = MINE_ASCII }
        return mineMap.map { row -> row.map { it.toChar() } }
    }

    private fun makeMineMap(mineMap: Array<IntArray>, coordinates: Coordinates) {
        setMineMap(mineMap, coordinates.row - 1, coordinates.col - 1)
        setMineMap(mineMap, coordinates.row - 1, coordinates.col)
        setMineMap(mineMap, coordinates.row - 1, coordinates.col + 1)
        setMineMap(mineMap, coordinates.row, coordinates.col - 1)
        setMineMap(mineMap, coordinates.row, coordinates.col + 1)
        setMineMap(mineMap, coordinates.row + 1, coordinates.col - 1)
        setMineMap(mineMap, coordinates.row + 1, coordinates.col)
        setMineMap(mineMap, coordinates.row + 1, coordinates.col + 1)
    }

    private fun setMineMap(mineMap: Array<IntArray>, row: Int, col: Int) {
        val isMin = row < 0 || col < 0
        val isMax = row > mineMap.lastIndex || col > mineMap[0].lastIndex
        if (isMin || isMax) return
        mineMap[row][col] += 1
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
        private const val MINE_ASCII = 42
        private const val ZERO_ASCII = 48
    }
}
