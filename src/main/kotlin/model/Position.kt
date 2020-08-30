package model

data class Position(val x: Int, val y: Int) {
    fun getAroundPositions(maxX: Int, maxY: Int): List<Position> {
        val operand = listOf(-1, 0, 1)
        return operand.flatMap { operandX ->
            operand.map { operandY ->
                Position(this.x + operandX, this.y + operandY)
            }
        }.filter { checkInvalidRange(it, maxX, maxY) }.toList()
    }

    private fun checkInvalidRange(targetPosition: Position, maxX: Int, maxY: Int): Boolean {
        return targetPosition.x in 0 until maxX && targetPosition.y in 0 until maxY && this != targetPosition
    }
}
