package minesweeper.strategy

import minesweeper.domain.MineBoard
import minesweeper.request.MinesCreateRequest

fun interface MineBoardCreateStrategy {
    fun create(request: MinesCreateRequest): MineBoard
}
