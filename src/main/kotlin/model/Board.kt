package model

class Board(private val boardSize: BoardSize, mineIndexes: List<Int>) {
    private val boardMap: Map<Coordinates, MineType>
    private val mineCountMap: Map<Coordinates, MineType>

    init {
        boardMap = setupBoardMap(mineIndexes)
        mineCountMap = getMineCountMap()
    }

    fun getMineCoordinates(): Set<Coordinates> = boardMap.filterValues { it == MineType.MINE }.keys

    fun getMineCountMap(): Map<Coordinates, MineType> =
        getInitBoard(MineType.ZERO).toMutableMap().also { mineMap ->
            val mineCoordinates = getMineCoordinates()
            setupMineAroundCount(mineCoordinates, mineMap)
            setupMine(mineCoordinates, mineMap)
        }

    private fun setupMine(mineCoordinates: Set<Coordinates>, mineMap: MutableMap<Coordinates, MineType>) =
        mineCoordinates.forEach { mineMap[it] = MineType.MINE }

    private fun setupMineAroundCount(mineCoordinates: Set<Coordinates>, mineMap: MutableMap<Coordinates, MineType>) =
        mineCoordinates.forEach { setupMineType(it, mineMap) }

    private fun setupMineType(mineCoordinate: Coordinates, mineMap: MutableMap<Coordinates, MineType>) {
        mineCoordinate.getAround(boardSize.row - 1, boardSize.col - 1).map {
            val ascii = mineMap[it]?.ascii ?: MineType.ZERO.ascii
            mineMap[it] = MineType.findByAscii(ascii + 1)
        }
    }

    fun getShowedArea(coordinates: Coordinates): Map<Coordinates, MineType> {
        val showedArea = mutableMapOf<Coordinates, MineType>()
        updateShowedArea(coordinates, showedArea)
        return showedArea
    }

    fun getInitBoard(defaultMineType: MineType): Map<Coordinates, MineType> =
        mutableMapOf<Coordinates, MineType>().also {
            for (row in 0 until boardSize.row) {
                for (col in 0 until boardSize.col) {
                    it[Coordinates(row, col)] = defaultMineType
                }
            }
        }

    private fun getValueInCoordinates(coordinates: Coordinates): MineType =
        mineCountMap.filterKeys { it == coordinates }.values.first()

    private fun updateShowedArea(coordinates: Coordinates, showedArea: MutableMap<Coordinates, MineType>) {
        showedArea[coordinates] = getValueInCoordinates(coordinates)
        if (getValueInCoordinates(coordinates) != MineType.ZERO) return
        coordinates.getAround(boardSize.row, boardSize.col).map {
            if (showedArea.contains(it)) return@map
            updateShowedArea(it, showedArea)
        }
    }

    private fun setupBoardMap(mineIndexes: List<Int>): Map<Coordinates, MineType> =
        getInitBoard(MineType.NONE).toMutableMap().also { board ->
            mineIndexes
                .map { Pair(it.div(boardSize.row), it.rem(boardSize.row)) }
                .map { board[Coordinates(it.first, it.second)] = MineType.MINE }
        }
}
