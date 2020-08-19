package model

class Board(boardSize: BoardSize, mineIndexes: List<Int>) {
    private val _linear = mutableListOf<MineType>()
    val grid: List<List<MineType>>

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

    fun convertToMineCount(): List<List<MineType>> {
        val mineMap = Array(grid.size) { IntArray(grid[0].size) { MineType.ZERO.ascii } }
        val mineCoordinates = getMineCoordinates()
        setupMineAround(mineCoordinates, mineMap)
        setupMine(mineCoordinates, mineMap)
        return mineMap.map { row -> row.map { MineType.findByAscii(it) } }
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
            if (grid[row][col] != MineType.MINE) continue
            mineCoordinates.add(Coordinates(row, col))
        }
    }

    private fun convertToGrid(boardSize: BoardSize) = _linear.chunked(boardSize.getRow())

    private fun setupLinear(boardSize: BoardSize, mineIndexes: List<Int>) {
        repeat(boardSize.get()) {
            _linear.add(MineType.NONE)
        }
        mineIndexes.forEach {
            _linear[it] = MineType.MINE
        }
    }
}
