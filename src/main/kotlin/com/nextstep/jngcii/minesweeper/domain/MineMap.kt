package com.nextstep.jngcii.minesweeper.domain

@JvmInline
value class MineMap private constructor(val rows: List<Row>) {
    companion object {
        fun build(locations: Locations): MineMap {
            val rows = getInitialTwoDimensionalList(
                locations.rowCount,
                locations.columnCount
            )

            locations.pairs.forEach {
                rows[it.y][it.x] = locations.check(it.x, it.y)
            }

            return MineMap(rows.map { Row(it) })
        }

        private fun getInitialTwoDimensionalList(
            rowCount: Int,
            columnCount: Int
        ): MutableList<MutableList<Boolean>> {
            return MutableList(rowCount) {
                MutableList(columnCount) { false }
            }
        }
    }
}
