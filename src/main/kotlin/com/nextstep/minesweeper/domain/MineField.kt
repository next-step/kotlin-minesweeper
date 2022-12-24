package com.nextstep.minesweeper.domain

class MineField(height: Int, width: Int, private val calculatePolicy: (Iterable<Int>) -> List<Int>) {
    private val _fields: Array<MineRow> = Array(height) { MineRow(width) }

    val fields: Array<MineRow>
        get() = _fields.copyOf()

    fun dispense(mineCounts: Int) {
        val width = Size(fields.first().size())
        val height = Size(fields.size)
        val total = width.multiple(height)

        validateDispense(total, mineCounts)
        val positions = MineDispenseHelper.calculatePositions(total, mineCounts, width, calculatePolicy)
        doDispense(positions)
    }

    private fun doDispense(positions: List<Position>) {
        for (position in positions) {
            fields[position.x].dispense(position.y)
        }
    }

    private fun validateDispense(total: Size, mineCounts: Int) {
        require(total.isGreaterThan(mineCounts)) { "지뢰 매설 수량 초과 하였습니다" }
        require(!isDispensed()) { "이미 매설 되었습니다" }
    }

    private fun isDispensed(): Boolean {
        return fields.any { it.isMined() }
    }
}
