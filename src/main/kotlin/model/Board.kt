package model

class Board(private val boardSize: BoardSize, mineIndexes: List<Int>) {
    private val minePositions: Map<Coordinates, MineType>
    private val numberOfMineAround: Map<Coordinates, MineType>
    private val _value = getInitBoard(MineType.NONE).toMutableMap()
    val value: Map<Coordinates, MineType> get() = _value

    init {
        minePositions = setupBoardMap(mineIndexes)
        numberOfMineAround = getMineCountMap()
    }

    fun getMineCoordinates(): Set<Coordinates> = minePositions.filterValues { it == MineType.MINE }.keys

    fun getMineCountMap(): Map<Coordinates, MineType> =
        getInitBoard(MineType.ZERO).toMutableMap().also { mineMap ->
            val mineCoordinates = getMineCoordinates()
            setupMineAroundCount(mineCoordinates, mineMap)
            setupMine(mineCoordinates, mineMap)
        }.toMap()

    private fun setupMine(mineCoordinates: Set<Coordinates>, mineMap: MutableMap<Coordinates, MineType>) =
        mineCoordinates.forEach { mineMap[it] = MineType.MINE }

    private fun setupMineAroundCount(mineCoordinates: Set<Coordinates>, mineMap: MutableMap<Coordinates, MineType>) =
        mineCoordinates.forEach { setupMineType(it, mineMap) }

    private fun setupMineType(mineCoordinate: Coordinates, mineMap: MutableMap<Coordinates, MineType>) {
        mineCoordinate.getAround(boardSize.row, boardSize.col).map {
            val mineType = mineMap[it] ?: MineType.ZERO
            mineMap[it] = MineType.findByNextType(mineType)
        }
    }

    fun updateShowedArea(coordinates: Coordinates) {
        makeShowedArea(coordinates)
    }

    private fun getInitBoard(defaultMineType: MineType): Map<Coordinates, MineType> =
        mutableMapOf<Coordinates, MineType>().also {
            for (row in 0..boardSize.row) {
                for (col in 0..boardSize.col) {
                    it[Coordinates(row, col)] = defaultMineType
                }
            }
        }.toMap()

    private fun getValueInCoordinates(coordinates: Coordinates): MineType =
        numberOfMineAround.filterKeys { it == coordinates }.values.first()

    private fun makeShowedArea(coordinates: Coordinates) {
        _value[coordinates] = getValueInCoordinates(coordinates)
        if (_value[coordinates] != MineType.ZERO) return
        coordinates.getAround(boardSize.row, boardSize.col).forEach {
            if (_value[it] == getValueInCoordinates(it)) return@forEach
            makeShowedArea(it)
        }
    }

    private fun setupBoardMap(mineIndexes: List<Int>): Map<Coordinates, MineType> =
        getInitBoard(MineType.NONE).toMutableMap().also { board ->
            mineIndexes
                .map { Pair(it.div(boardSize.row + 1), it.rem(boardSize.row + 1)) }
                .map { board[Coordinates(it.first, it.second)] = MineType.MINE }
        }.toMap()
}
