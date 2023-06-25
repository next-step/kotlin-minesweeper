package minesweeper.fixture

import minesweeper.domain.MineBoard
import minesweeper.domain.SymbolType
import minesweeper.request.MinesCreateRequest
import minesweeper.strategy.MineBoardCreateStrategy

object TestMineBoardCreateStrategy : MineBoardCreateStrategy() {
    private var items: List<SymbolType> = mutableListOf()

    fun updateBoardSetUp(input: List<SymbolType>) {
        items = input
    }

    override fun create(request: MinesCreateRequest): MineBoard {
        return MineBoard(
            width = request.width,
            height = request.height,
            lines = convertToLines(symbols = items, request)
        )
    }
}
