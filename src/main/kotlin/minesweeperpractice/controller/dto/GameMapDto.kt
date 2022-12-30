package minesweeperpractice.controller.dto

import minesweeperpractice.domain.CleanBlock
import minesweeperpractice.domain.GameMap
import minesweeperpractice.domain.MineBlock
import minesweeperpractice.view.model.BlockModel
import minesweeperpractice.view.model.HideBlockModel
import minesweeperpractice.view.model.MineBlockModel

data class GameMapDisplayDto(
    val width: Int,
    val blocks: List<BlockModel>
) {
    companion object {
        fun from(gameMap: GameMap): GameMapDisplayDto {
            return GameMapDisplayDto(
                width = gameMap.width,
                blocks = gameMap.blocks.blocks.map {
                    when (it) {
                        is CleanBlock -> return@map HideBlockModel()
                        is MineBlock -> return@map MineBlockModel()
                    }
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
