package minesweeper.controller.dto

import minesweeper.domain.GameMap
import minesweeper.domain.MineSettingStrategy

data class BuildMapRequest(
    val height: Int,
    val width: Int,
    val mineCount: Int
) {
    fun toGameMap(buildStrategy: MineSettingStrategy) = GameMap.of(height, width, mineCount, buildStrategy)
}
