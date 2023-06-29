package mine.sweeper

import mine.sweeper.domain.MapInitializer
import mine.sweeper.domain.Vulture
import mine.sweeper.view.OutputView

class MineSweeperGame(
    height: Int,
    width: Int,
) {
    private val map = MapInitializer(height, width).createMap()

    fun printEntireMap() {
        OutputView.printMap(map.entireMap())
    }

    fun setMines(mines: Int) {
        val vulture = Vulture(mines)
        vulture.layingMines(map)
    }
}
