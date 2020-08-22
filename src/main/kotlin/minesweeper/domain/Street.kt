package minesweeper.domain

data class Street(val streetNumber: Int, val width: Int) {
    private var _spots: MutableList<Spot>
    val spots: List<Spot>
        get() = _spots.toList()

    init {
        _spots = MutableList(width) { nthPosition -> MineLessSpot(streetNumber, nthPosition) }
    }

    fun setMinePosition(positionChoosingStrategy: PositionChoosingStrategy): Boolean {
        if (isFullOfMines()) return true

        var nth = getNthPosition(positionChoosingStrategy)
        while (_spots[nth] is MineSpot) {
            nth = getNthPosition(positionChoosingStrategy)
        }
        _spots[nth] = MineSpot(streetNumber, nth)
        return false
    }

    private fun getNthPosition(positionChoosingStrategy: PositionChoosingStrategy) =
        positionChoosingStrategy.getPosition(width)

    fun calculateMineCountAround(spots: List<Spot>) {
        this.spots.forEach {
            val left = it.nthPosition - 1 // 좌
            val right = it.nthPosition + 1 // 우

            if (0 <= left) it.addMineCount(spots[left])
            if (right < this.width) it.addMineCount(spots[right])

            it.addMineCount(spots[it.nthPosition]) // 자신의 위치
        }
    }

    fun isFullOfMines(): Boolean = mineCount() == width

    fun mineCount() = spots.count { it is MineSpot }

    override fun toString(): String = spots.joinToString(" ")
}
