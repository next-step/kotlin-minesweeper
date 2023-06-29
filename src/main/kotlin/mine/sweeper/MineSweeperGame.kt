package mine.sweeper

import mine.sweeper.domain.MapSetup
import mine.sweeper.domain.SweeperMap
import mine.sweeper.domain.Vulture
import mine.sweeper.view.OutputView

class MineSweeperGame(
    height: Int,
    width: Int
) {
    private val setup = MapSetup(height, width)
    private val map = SweeperMap(setup)

    fun printEntireMap() {
        OutputView.printMap(map.entireMap())
    }

    fun setMines(mines: Int) {
        val vulture = Vulture(mines)
        vulture.layingMines(map)
    }
}
