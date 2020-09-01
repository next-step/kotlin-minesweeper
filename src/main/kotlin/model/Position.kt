package model

data class Position(val x: Int, val y: Int) {
    companion object {
        fun getAroundPositions(position: Position, maxX: Int, maxY: Int): List<Position> {
            val operand = listOf(-1, 0, 1)
            return operand.flatMap { operandX ->
                operand.map { operandY ->
                    Position(position.x + operandX, position.y + operandY)
                }
            }.filter { checkInvalidRange(position, it, maxX, maxY) }.toList()
        }

        private fun checkInvalidRange(position: Position, targetPosition: Position, maxX: Int, maxY: Int): Boolean {
            return targetPosition.x in 0 until maxX && targetPosition.y in 0 until maxY && position != targetPosition
        }
    }
}
