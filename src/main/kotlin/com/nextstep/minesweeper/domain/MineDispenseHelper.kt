package com.nextstep.minesweeper.domain

class MineDispenseHelper {
    companion object {
        fun calculatePositions(
            total: Size,
            mineCounts: Int,
            width: Size,
            calculatePolicy: (Iterable<Int>) -> List<Int>
        ): List<Position> {
            val target = (0 until total.value).toList()
            val positions = calculatePolicy.invoke(target).take(mineCounts)
            return positions.map { Position(it / width.value, it % width.value) }
        }

        fun defaultCalculatePolicy(): (Iterable<Int>) -> List<Int> = { it -> it.shuffled() }
    }
}
