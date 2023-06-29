package tdd.minesweeper.fixture

import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.Rows
import tdd.minesweeper.provider.RowsProvider

object RowsFixtureProvider : RowsProvider {
    private var fixtureRows: Rows = Rows(emptyList())

    fun updateFixtureRows(rows: Rows) {
        fixtureRows = rows
    }

    override fun provide(area: Area, mineCapacity: Int): Rows = fixtureRows
}
