package minesweeper.application

import minesweeper.domain.MineBoard
import minesweeper.domain.dsl.buildMineBoard
import minesweeper.request.MineBoardCreateRequest
import minesweeper.strategy.MineBoardCreateStrategy

class MineSweeperService(
    private val mineBoardCreateStrategy: MineBoardCreateStrategy
) {

    fun createMineBoard(request: MineBoardCreateRequest): MineBoard =
        buildMineBoard {
            width(request.width)
            height(request.height)
            mineCapacity(request.mineCapacity)
            strategy(mineBoardCreateStrategy)
        }
}
