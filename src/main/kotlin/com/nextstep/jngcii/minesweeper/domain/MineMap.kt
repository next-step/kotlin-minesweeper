package com.nextstep.jngcii.minesweeper.domain

@JvmInline
value class MineMap private constructor(val rows: List<Row>) {
    companion object {
        fun build(locations: Locations): MineMap {
            val meta = locations.meta
            val rows = List(meta.totalArea) { false }
                .chunked(meta.columnCount) { it.toMutableList() }
                .toMutableList()

            locations.locations.forEach {
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
