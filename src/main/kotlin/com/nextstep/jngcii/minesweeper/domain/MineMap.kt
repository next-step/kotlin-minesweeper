package com.nextstep.jngcii.minesweeper.domain

@JvmInline
value class MineMap private constructor(val rows: List<Row>) {
    companion object {
        fun from(locations: Locations): MineMap {
            val meta = locations.meta
            val boolean2dList = List(meta.totalArea) { false }
                .chunked(meta.columnCount) { it.toMutableList() }

            locations.locations.forEach {
                boolean2dList[it.y][it.x] = locations.check(it.x, it.y)
            }

            return MineMap(Row.from(boolean2dList))
        }
    }
}
