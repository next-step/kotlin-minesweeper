package com.nextstep.minesweeper.domain

class MineField(height: Int, width: Int) {
    private val _fields: Array<MineRow> = Array(height) { MineRow(width) }

    val fields: Array<MineRow>
        get() = _fields.copyOf()

    fun dispense(mineCounts: Int) {
        val width = fields.first().size()
        val height = fields.size
        val total = width * height

        validateDispense(total, mineCounts)
        val positions = calculatePositions(total, mineCounts, width)
        doDispense(positions)
    }

    private fun calculatePositions(
        total: Int,
        mineCounts: Int,
        width: Int
    ): List<Pair<Int, Int>> {
        val positions = (0 until total).toList().shuffled().take(mineCounts)
        return positions.map { Pair(it / width, it % width) }
    }

    private fun doDispense(positions: List<Pair<Int, Int>>) {
        for (pair in positions) {
            val row = pair.first
            val col = pair.second
            fields[row].dispense(col)
        }
    }

    private fun validateDispense(total: Int, mineCounts: Int) {
        require(total >= mineCounts) { "지뢰 매설 수량 초과 하였습니다" }
        require(!isDispensed()) { "이미 매설 되었습니다" }
    }

    private fun isDispensed(): Boolean {
        return fields.any { it.isMined() }
    }
}
