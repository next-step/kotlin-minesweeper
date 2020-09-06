package model

data class Position(val x: Int, val y: Int) {
    fun getAround(mapSize: MapSize): List<Position> {
        val operand = listOf(-1, 0, 1)
        return operand.flatMap { x ->
            operand.map { y ->
                Position(this.x + x, this.y + y)
            }
        }.filter { positionInvalid(it, mapSize) }
    }

    private fun positionInvalid(position: Position, mapSize: MapSize): Boolean {
        return position.x in 0 until mapSize.maxX && position.y in 0 until mapSize.maxY && position != this
    }
}
