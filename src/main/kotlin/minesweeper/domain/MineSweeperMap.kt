package minesweeper.domain

import minesweeper.domain.Height.Companion.MINIMUM_HEIGHT

class MineSweeperMap(private val height: Height, private val width: Width) {

    fun createPosition(): List<Position> {
        return (MINIMUM_HEIGHT..height.value).map {
            createRow(it)
        }.flatten()
    }

    private fun createRow(y: Int): List<Position> {
        return (1..width.value).map { Position(it, y) }
    }
}
