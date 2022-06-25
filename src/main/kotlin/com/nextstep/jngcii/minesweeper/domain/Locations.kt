package com.nextstep.jngcii.minesweeper.domain

data class Locations(
    val meta: MineMapMeta
) {
    val pairs: List<Location> = meta.toLocationList()

    fun pickMines(count: Int, strategy: PickStrategy) {
        strategy.pick(this, count)
    }

    fun check(x: Int, y: Int): Boolean {
        return pairs
            .find { it.x == x && it.y == y }
            ?.isMine
            ?: throw IllegalArgumentException("해당 좌표에 대한 Location이 존재하지 않습니다. (x:$x, y:$y)")
    }

    private fun MineMapMeta.toLocationList(): List<Location> {
        val xIndexes = List(columnCount) { it }
        val yIndexes = List(rowCount) { it }

        return xIndexes.flatMap { x ->
            yIndexes.map { y -> Location(x, y) }
        }
    }
}
