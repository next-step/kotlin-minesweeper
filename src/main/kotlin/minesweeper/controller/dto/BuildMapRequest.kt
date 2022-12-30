package minesweeper.controller.dto

import minesweeper.domain.GameMap

data class BuildMapRequest(
    val height: Int,
    val width: Int,
    val mineCount: Int
) {
    fun toGameMap() = GameMap.of(height, width, mineCount)
}
