package model

class Board(private val boardSize: BoardSize, mineIndexes: List<Int>) {
    private val _minePositions = mutableMapOf<Coordinates, MineType>()
    val minePositions: BoardMap get() = BoardMap(_minePositions)

    private val _numberOfMineAround = mutableMapOf<Coordinates, MineType>()
    val numberOfMineAround: BoardMap get() = BoardMap(_numberOfMineAround)

    private val _map = mutableMapOf<Coordinates, MineType>()
    val map: BoardMap get() = BoardMap(_map)

    init {
        setupBoardMap(mineIndexes)
        setupMineCountMap()
        setupMap()
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

    private fun setupMap() {
        for (row in 0..boardSize.row) {
            for (col in 0..boardSize.col) {
                _map[Coordinates(row, col)] = MineType.NONE
            }
        }
    }

    private fun makeShowedArea(coordinates: Coordinates) {
        _map[coordinates] = numberOfMineAround.getValue(coordinates)
        if (_map[coordinates] != MineType.ZERO) return
        coordinates.getAround(boardSize.row, boardSize.col).forEach {
            if (_map[it] == numberOfMineAround.getValue(coordinates)) return@forEach
            makeShowedArea(it)
        }
    }

    private fun setupBoardMap(mineIndexes: List<Int>) {
        mineIndexes
            .map { Pair(it.div(boardSize.row + 1), it.rem(boardSize.row + 1)) }
            .forEach { _minePositions[Coordinates(it.first, it.second)] = MineType.MINE }
    }
}
