package minesweeper.inputdata

data class PositiveNumber(val size: Int) {
    init {
        require(size > 0) { "0보다 커야합니다." }
    }

    operator fun times(operand: PositiveNumber): PositiveNumber {
        return PositiveNumber(size * operand.size)
    }

    operator fun compareTo(operand: PositiveNumber): Int {
        return size.compareTo(operand.size)
    }
}
