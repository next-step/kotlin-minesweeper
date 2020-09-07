package model

class Game(private val mapSize: MapSize, private val mineCount: MineCount) {
    private val _viewMap: MutableMap<Position, CellType> = mutableMapOf()
    val viewMap: Map get() = Map(_viewMap)

    private val _countMap: MutableMap<Position, CellType> = mutableMapOf()
    val countMap: Map get() = Map(_countMap)

    init {
        createBaseMap()
        createMines()
    }

    fun openMap(position: Position) {
        _viewMap[position] = countMap.getPositionValue(position)
        if (_countMap[position] == CellType.MINE) throw Exception("You lose")
        if (_countMap[position] != CellType.ZERO) return
        position.getAround(mapSize).forEach {
            if (_countMap[it] == CellType.ZERO) openMap(it) else return
        }
    }

    private fun createBaseMap() {
        for (x in 0 until mapSize.maxX) {
            for (y in 0 until mapSize.maxY) {
                _viewMap[Position(x, y)] = CellType.NONE
                _countMap[Position(x, y)] = CellType.ZERO
            }
        }
    }

    private fun createMines() {
        _countMap.toList().shuffled().take(mineCount.number).forEach {
            calculateMineAroundCount(it.first)
        }
    }

    private fun calculateMineAroundCount(position: Position) {
        _countMap[position] = CellType.MINE
        position.getAround(mapSize).forEach {
            _countMap[it] = CellType.nextValue(countMap.getPositionValue(it))
        }
    }

    override fun toString(): String {
        return viewMap.toString()
    }
}
