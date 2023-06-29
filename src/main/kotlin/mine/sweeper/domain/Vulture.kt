package mine.sweeper.domain

class Vulture(val mines: Int) {
    init {
        require(mines > 0)
    }

    fun layingMines(map: SweeperMap) {
        repeat(mines) {
            val (height, width) = map.randomField()
            map.setMine(height, width)
        }
    }
}
