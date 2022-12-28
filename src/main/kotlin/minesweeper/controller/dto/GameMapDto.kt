package minesweeper.controller.dto

import minesweeper.domain.CleanBlock
import minesweeper.domain.GameMap
import minesweeper.domain.MineBlock

data class GameMapDisplayDto(
    val width: Int,
    val patterns: List<String>
) {
    companion object {
        fun from(gameMap: GameMap): GameMapDisplayDto {
            return GameMapDisplayDto(
                width = gameMap.width,
                patterns = gameMap.blocks.blocks.map {
                    if (it.javaClass == CleanBlock::class.java) {
                        return@map "□"
                    }
                    if (it.javaClass == MineBlock::class.java) {
                        return@map "※"
                    }
                    throw IllegalStateException()
                }
            )
        }
    }
}

data class GameMapRequestDto(
    val height: Int,
    val width: Int,
    val mineCount: Int
) {
    fun toGameMap() = GameMap.of(height, width, mineCount)
}
