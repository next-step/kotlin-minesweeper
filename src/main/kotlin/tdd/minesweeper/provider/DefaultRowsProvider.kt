package tdd.minesweeper.provider

import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.Row
import tdd.minesweeper.domain.Rows
import tdd.minesweeper.domain.SymbolPoint
import tdd.minesweeper.domain.type.SymbolType

object DefaultRowsProvider : RowsProvider {
    override fun provide(area: Area, mineCapacity: Int): Rows =
        buildList {
            require(area.size > mineCapacity) {
                "지뢰 갯수가 지뢰판의 크기보다 많을 수 없습니다. [Size: ${area.size}, MineCapacity: $mineCapacity]"
            }

            repeat(mineCapacity) { add(SymbolType.MINE) }
            (mineCapacity until area.size).forEach {
                add(SymbolType.ZERO)
            }
        }.shuffled()
            .chunked(area.width)
            .mapIndexed(::convertToRows)
            .let(::Rows)

    private fun convertToRows(y: Int, symbolTypes: List<SymbolType>): Row =
        symbolTypes.mapIndexed { x, symbol ->
            SymbolPoint(x = x, y = y, symbol)
        }.let(::Row)
}
