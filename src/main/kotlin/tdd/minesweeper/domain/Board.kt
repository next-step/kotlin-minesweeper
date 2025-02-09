package tdd.minesweeper.domain

import tdd.minesweeper.domain.strategy.DefaultShouldOpenLocationFinder
import tdd.minesweeper.domain.strategy.ShouldOpenLocationFinder

data class Board(
    val area: Area,
    val cells: Cells,
    private val shouldOpenLocationFinder: ShouldOpenLocationFinder = DefaultShouldOpenLocationFinder(),
) {
    fun countOfClosed(): Int = cells.count { !it.isOpen() }

    fun countOfMineOpened(): Int = cells.count { it is MineCell }

    fun open(location: Location): Board {
        validateLocation(location)

        val shouldOpen = shouldOpenLocationFinder.findAllShouldOpen(this, location)

        return this.copy(cells = applyOpen(shouldOpen))
    }

    private fun validateLocation(location: Location) {
        require(location.isValid(area)) {
            "보드 내의 위치가 아닙니다: location=$location"
        }
    }

    private fun applyOpen(shouldOpen: Set<Location>): Cells {
        val shouldOpenIndexes = shouldOpen.map { it.toIndex(area.width) }.toSet()
        val result = cells.toMutableList()

        shouldOpenIndexes.forEach { index -> result[index] = cells[index].open() }

        return Cells(result.toList())
    }
}
