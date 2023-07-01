package mine.sweeper.domain

class Vulture(private val map: SweeperMap) {

    fun layingMines(mines: Int) {
        require(mines > 0)

        repeat(mines) {
            map.setMineToRandomPosition()
        }
    }
}
