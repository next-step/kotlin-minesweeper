package model

class Board(private val boardSize: BoardSize, mineIndexes: List<Int>) {
    private val _minePositions = mutableMapOf<Coordinates, MineType>()
    val minePositions: Map<Coordinates, MineType> get() = _minePositions

    private val _numberOfMineAround = mutableMapOf<Coordinates, MineType>()
    val numberOfMineAround: Map<Coordinates, MineType> get() = _numberOfMineAround

    private val _value = mutableMapOf<Coordinates, MineType>()
    val value: Map<Coordinates, MineType> get() = _value

    init {
        setupBoardMap(mineIndexes)
        setupMineCountMap()
        setupValue()
    }

    fun updateShowedArea(coordinates: Coordinates) {
        makeShowedArea(coordinates)
    }

    private fun setupMineCountMap() {
        setupMineAroundCount()
        setupMine()
    }

    private fun setupMine() =
        _minePositions.forEach { _numberOfMineAround[it.key] = MineType.MINE }

    private fun setupMineAroundCount() =
        _minePositions.forEach { setupMineType(it.key) }

    private fun setupMineType(mineCoordinate: Coordinates) {
        mineCoordinate.getAround(boardSize.row, boardSize.col).map {
            _numberOfMineAround.computeIfAbsent(it) { MineType.ZERO }
            val mineType = _numberOfMineAround[it] ?: MineType.ZERO
            _numberOfMineAround[it] = MineType.findByNextType(mineType)
        }
    }

    private fun setupValue() {
        for (row in 0..boardSize.row) {
            for (col in 0..boardSize.col) {
                _value[Coordinates(row, col)] = MineType.NONE
            }
        }
    }

    private fun getValueInCoordinates(coordinates: Coordinates): MineType =
        numberOfMineAround.filterKeys { it == coordinates }.values.let {
            if (it.isEmpty()) MineType.ZERO else it.first()
        }

    private fun makeShowedArea(coordinates: Coordinates) {
        _value[coordinates] = getValueInCoordinates(coordinates)
        if (_value[coordinates] != MineType.ZERO) return
        coordinates.getAround(boardSize.row, boardSize.col).forEach {
            if (_value[it] == getValueInCoordinates(it)) return@forEach
            makeShowedArea(it)
        }
    }

    private fun setupBoardMap(mineIndexes: List<Int>) {
        mineIndexes
            .map { Pair(it.div(boardSize.row + 1), it.rem(boardSize.row + 1)) }
            .forEach { _minePositions[Coordinates(it.first, it.second)] = MineType.MINE }
    }
}
