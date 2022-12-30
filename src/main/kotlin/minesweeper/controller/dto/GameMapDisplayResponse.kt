package minesweeper.controller.dto

import minesweeper.domain.CleanBlock
import minesweeper.domain.GameMap
import minesweeper.domain.MineBlock
import minesweeper.view.model.BlockViewModel
import minesweeper.view.model.HideBlockViewModel
import minesweeper.view.model.MineBlockViewModel

data class GameMapDisplayResponse(
    val width: Int,
    val blocksModels: List<BlockViewModel>
) {

    companion object {
        fun from(gameMap: GameMap): GameMapDisplayResponse {
            val record = gameMap.blockTable.record
            val mapCords = record.keys
            val blocks = record.values

            return GameMapDisplayResponse(
                mapCords.filter { it.y == 0 }.size,
                blocks.map { block ->
                    when (block) {
                        is CleanBlock -> HideBlockViewModel()
                        is MineBlock -> MineBlockViewModel()
                    }
                }
            )
        }
    }
}
