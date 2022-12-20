package com.nextstep.minesweeper.domain

class MineField(height: Int, width: Int) {
    private val _fields: Array<Array<Mine>> = Array(height) { Array(width) { Mine.GROUND } }

    val fields: Array<Array<Mine>>
        get() = _fields.copyOf()

    fun dispense(mineCounts: Int) {
        val width = fields.size
        val height = fields.first().size
        val total = width * height

        validateDispense(total, mineCounts)
        val positions = calculatePositions(total, mineCounts, height)
        doDispense(positions)
    }

    private fun calculatePositions(
        total: Int,
        mineCounts: Int,
        height: Int
    ): List<Pair<Int, Int>> {
        val positions = (0 until total - 1).toList().shuffled().take(mineCounts)
        return positions.map { Pair(it / height, it % height) }
    }

    private fun doDispense(positions: List<Pair<Int, Int>>) {
        for (pair in positions) {
            val row = pair.first
            val col = pair.second
            fields[row][col] = Mine.MINED
        }
    }

    private fun validateDispense(total: Int, mineCounts: Int) {
        require(total >= mineCounts) { "지뢰 매설 수량 초과 하였습니다" }
        require(!isDispensed()) { "이미 매설 되었습니다" }
    }

    private fun isDispensed(): Boolean {
        return fields.flatten().any { it == Mine.MINED }
    }
}
