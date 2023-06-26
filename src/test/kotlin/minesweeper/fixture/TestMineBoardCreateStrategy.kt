package minesweeper.fixture

import minesweeper.domain.Lines
import minesweeper.domain.MineBoard
import minesweeper.request.MinesCreateRequest
import minesweeper.strategy.MineBoardCreateStrategy

object TestMineBoardCreateStrategy : MineBoardCreateStrategy() {
    private var items: Lines = Lines(emptyList())

    fun updateBoardSetUp(input: Lines) {
        items = input
    }

    override fun create(request: MinesCreateRequest): MineBoard {
        return MineBoard(
            width = request.width,
            height = request.height,
            lines = items
        )
    }
}
