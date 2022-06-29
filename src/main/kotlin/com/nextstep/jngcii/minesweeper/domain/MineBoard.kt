package com.nextstep.jngcii.minesweeper.domain

data class MineBoard(
    val meta: MineBoardMeta
) {
    val locations: List<Location> = meta.toLocationList()
    val locationsByRow = locations
        .groupBy { it.y }
        .values
        .toList()

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

    fun recordRisk() {
        locations.filter { it.isMine }
            .also { println(it) }
            .map { mines -> mines.aroundPairs }
            .forEach { pairs -> pairs.increaseRisks() }
    }

    private fun List<Pair<Int, Int>>.increaseRisks() {
        this.forEach { (x, y) ->
            locations.find { x == it.x && y == it.y && !it.isMine }?.increaseRisk()
        }
    }

    private fun MineBoardMeta.toLocationList(): List<Location> {
        val xIndexes = List(columnCount) { it }
        val yIndexes = List(rowCount) { it }

        return xIndexes.flatMap { x ->
            yIndexes.map { y -> Location(x, y) }
        }
    }
}
