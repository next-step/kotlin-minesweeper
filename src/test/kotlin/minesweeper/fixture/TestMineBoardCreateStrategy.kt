package minesweeper.fixture

import minesweeper.domain.MineBoard
import minesweeper.domain.Rows
import minesweeper.request.MinesCreateRequest
import minesweeper.strategy.MineBoardCreateStrategy

object TestMineBoardCreateStrategy : MineBoardCreateStrategy() {
    private var items: Rows = Rows(emptyList())

    fun updateBoardSetUp(input: Rows) {
        items = input
    }

    override fun create(request: MinesCreateRequest): MineBoard {
        return MineBoard(
            width = request.width,
            height = request.height,
            rows = items
        )
    }
}
