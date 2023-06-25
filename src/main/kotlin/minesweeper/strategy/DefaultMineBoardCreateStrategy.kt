package minesweeper.strategy

import minesweeper.domain.MineBoard
import minesweeper.domain.SymbolType
import minesweeper.request.MinesCreateRequest

object DefaultMineBoardCreateStrategy : MineBoardCreateStrategy() {

    override fun create(request: MinesCreateRequest): MineBoard {
        val area = request.width * request.height

        require(area >= request.mineCapacity) {
            "지뢰의 갯수는 지뢰찾기 판 넓이보다 많을 수 없습니다. Area: $area, Mine: ${request.mineCapacity}"
        }

        val symbols = (0 until area).map {
            if (it < request.mineCapacity) SymbolType.MINE else SymbolType.BLIND
        }.shuffled()

        return MineBoard(
            height = request.height, width = request.width, lines = convertToLines(symbols, request)
        )
    }
}
