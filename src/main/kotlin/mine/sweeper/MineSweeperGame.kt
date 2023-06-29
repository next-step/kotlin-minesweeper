package mine.sweeper

import mine.sweeper.domain.MapSetup
import mine.sweeper.domain.SweeperMap
import mine.sweeper.domain.Vulture
import mine.sweeper.view.OutputView

class MineSweeperGame(
    inputHeight: () -> Int,
    inputWidth: () -> Int
) {
    private val setup = MapSetup(inputHeight(), inputWidth())
    private val map = SweeperMap(setup)

    fun printEntireMap() {
        OutputView.printMap(map.entireMap())
    }

    fun setMines(inputMines: () -> Int) {
        val vulture = Vulture(inputMines())
        vulture.layingMines(map)
    }
}
