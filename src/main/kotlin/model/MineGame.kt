package model

class MineGame(private val mapSize: MapSize, private val mineCount: MineCount) {
    private val _map: MutableList<Item> = mutableListOf()
    val map: Map get() = Map(_map)

    init {
        createBaseMap()
        createMines()
    }

    fun openMap(position: Position) {
        val item = findItem(position).apply {
            isOpen()
        }
        if (item.type.isMine()) throw Exception("You lose")
        if (item.type != Type.ZERO) {
            return
        }
        position.getAroundPosition(mapSize).forEach {
            if (findItem(it).isOpen) return@forEach
            openMap(it)
        }
    }

    fun win(): Boolean {
        return _map.filter { it.type == Type.NONE }.size == _map.filter { it.type.isMine() }.size
    }

    private fun createBaseMap() {
        for (x in 0 until mapSize.lengthX.value) {
            for (y in 0 until mapSize.lengthY.value) {
                _map.add(Item(Position(x, y), Type.ZERO))
            }
        }
    }

    private fun createMines() {
        _map.toList().shuffled().take(mineCount.value).forEach {
            calculateMineAroundCount(it.position)
        }
    }

    private fun calculateMineAroundCount(position: Position) {
        findItem(position).type = Type.MINE
        position.getAroundPosition(mapSize).forEach {
            val item = findItem(it)
            item.type = item.type.nextValue()
        }
    }

    private fun findItem(position: Position): Item {
        return _map.find { it.position == position } ?: throw Exception("해당 좌표에 값이 없습니다.")
    }

    override fun toString(): String {
        return map.toString()
    }
}
