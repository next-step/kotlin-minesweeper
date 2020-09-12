package model

data class Position(val x: Int, val y: Int) {
    fun getAroundPosition(mapSize: MapSize): List<Position> {
        val operand: List<Int> = listOf(-1, 0, 1)
        return operand.flatMap { x ->
            operand.map { y ->
                Position(this.x + x, this.y + y)
            }
        }.filter { invalidPositionCheck(it, mapSize) }
    }

    private fun invalidPositionCheck(position: Position, mapSize: MapSize): Boolean {
        return position.x in 0 until mapSize.lengthX.value && position.y in 0 until mapSize.lengthY.value && position != this
    }
}
