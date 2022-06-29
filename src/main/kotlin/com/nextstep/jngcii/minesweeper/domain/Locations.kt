package com.nextstep.jngcii.minesweeper.domain

data class Locations(
    val meta: MineMapMeta
) {
    val locations: List<Location> = meta.toLocationList()

    fun pickMines(count: Int, strategy: OrderStrategy) {
        check(locations.size >= count) {
            "${locations.size}개 중 ${count}개를 고를 수 없습니다."
        }

        val picked = strategy.pick(
            total = locations.size,
            count = count
        )

        picked.forEach { locations[it].pick() }
    }

    fun check(x: Int, y: Int): Boolean {
        return locations
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
