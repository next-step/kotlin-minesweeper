package mine.sweeper

import mine.sweeper.domain.MapInitializer
import mine.sweeper.domain.MapSize
import mine.sweeper.domain.Vulture
import mine.sweeper.view.OutputView

class MineSweeperGame(mapSize: MapSize) {
    private val map = MapInitializer(mapSize).createMap()

    fun printEntireMap() {
        OutputView.printMap(map.entireMap())
    }

    fun setMines(mines: Int) {
        val vulture = Vulture(map)
        vulture.layingMines(mines)
    }
}
