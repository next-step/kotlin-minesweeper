package minesweeper.domain

import minesweeper.domain.Height.Companion.MINIMUM_HEIGHT

class MineSweeperMap(private val height: Height, private val width: Width) {

    fun createPosition(): MineSweeperIndexes {
        return MineSweeperIndexes(
            (MINIMUM_HEIGHT..height.value).map {
                createRow(it)
            }.flatten()
        )
    }

    private fun createRow(y: Int): List<MineSweeperIndex> {
        return (1..width.value).map { MineSweeperIndex(Position(it, y)) }
    }
}
