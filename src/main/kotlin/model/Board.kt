package model

class Board(boardSize: BoardSize, mineIndexes: List<Int>) {
    private val grid: List<List<MineType>>
    private val boardMap: Map<Coordinates, MineType>

    init {
        grid = getGrid(boardSize, mineIndexes)
        boardMap = setupBoardMap(boardSize, mineIndexes)
    }

    fun getMineCoordinates(): Set<Coordinates> = boardMap.filterValues { it == MineType.MINE }.keys

    fun convertToMineCount(): Map<Coordinates, MineType> {
        val mineCounts = Array(grid.size) { IntArray(grid[0].size) { MineType.ZERO.ascii } }.also {
            val mineCoordinates = getMineCoordinates()
            setupMineAround(mineCoordinates, it)
            setupMine(mineCoordinates, it)
        }
        val mineMap = mutableMapOf<Coordinates, MineType>()
        mineCounts.mapIndexed { row, rowElements ->
            rowElements.mapIndexed { col, mineType ->
                mineMap[Coordinates(row, col)] = MineType.findByAscii(mineType)
            }
        }
        return mineMap
    }

    private fun setupMine(mineCoordinates: Set<Coordinates>, mineMap: Array<IntArray>) {
        mineCoordinates.forEach { mineMap[it.row][it.col] = MineType.MINE.ascii }
    }

    private fun setupMineAround(mineCoordinates: Set<Coordinates>, mineMap: Array<IntArray>) {
        mineCoordinates.forEach { mineCoordinate ->
            val aroundMine = mineCoordinate.getAround(grid.lastIndex, grid[0].lastIndex)
            aroundMine.map { mineMap[it.row][it.col] += 1 }
        }
    }

    private fun setupBoardMap(boardSize: BoardSize, mineIndexes: List<Int>): Map<Coordinates, MineType> {
        val grid = getGrid(boardSize, mineIndexes)
        val coordinatesToMineType = mutableMapOf<Coordinates, MineType>()
        for (row in grid.indices) {
            makeBoardMap(grid, row, coordinatesToMineType)
        }
        return coordinatesToMineType
    }

    private fun makeBoardMap(
        grid: List<List<MineType>>,
        row: Int,
        coordinatesToMineType: MutableMap<Coordinates, MineType>
    ) {
        for (col in grid[0].indices) {
            setupBoardMap(grid, Coordinates(row, col), coordinatesToMineType)
        }
    }

    private fun setupBoardMap(
        grid: List<List<MineType>>,
        coordinates: Coordinates,
        coordinatesToMineType: MutableMap<Coordinates, MineType>
    ) {
        if (grid[coordinates.row][coordinates.col] == MineType.MINE) {
            coordinatesToMineType[coordinates] = MineType.MINE
            return
        }
        coordinatesToMineType[coordinates] = MineType.NONE
    }

    private fun getGrid(boardSize: BoardSize, mineIndexes: List<Int>): List<List<MineType>> {
        val linear = mutableListOf<MineType>()
        repeat(boardSize.get()) {
            linear.add(MineType.NONE)
        }
        mineIndexes.forEach {
            linear[it] = MineType.MINE
        }
        return linear.chunked(boardSize.getRow())
    }
}
