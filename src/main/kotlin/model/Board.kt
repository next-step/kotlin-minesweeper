package model

class Board(boardSize: BoardSize, mineIndexes: List<Int>) {
    val grid: List<List<MineType>>
    private val boardMap: Map<Coordinates, MineType>
    private val mineCountMap: Map<Coordinates, MineType>

    init {
        grid = getGrid(boardSize, mineIndexes)
        boardMap = setupBoardMap()
        mineCountMap = convertToMineCount()
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

    fun getShowedArea(coordinates: Coordinates): Map<Coordinates, MineType> {
        val showedArea = mutableMapOf<Coordinates, MineType>()
        updateShowedArea(coordinates, showedArea)
        return showedArea
    }

    fun getInitBoard(): Map<Coordinates, MineType> =
        mutableMapOf<Coordinates, MineType>().also {
            for (row in grid.indices) {
                for (col in grid[0].indices) {
                    it[Coordinates(row, col)] = MineType.NONE
                }
            }
        }

    private fun getValueInCoordinates(coordinates: Coordinates): MineType =
        mineCountMap.filterKeys { it == coordinates }.values.first()

    private fun updateShowedArea(coordinates: Coordinates, showedArea: MutableMap<Coordinates, MineType>) {
        showedArea[coordinates] = getValueInCoordinates(coordinates)
        if (getValueInCoordinates(coordinates) != MineType.ZERO) return
        coordinates.getAround(grid.size, grid[0].size).map {
            if (showedArea.contains(it)) return@map
            updateShowedArea(it, showedArea)
        }
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

    private fun setupBoardMap(): Map<Coordinates, MineType> {
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
