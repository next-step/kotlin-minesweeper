package model

data class Position(private val x: Int, private val y: Int) {
    fun getAroundPosition(): List<Position> {
        val operand: List<Int> = listOf(-1, 0, 1)
        return operand.flatMap { x ->
            operand.map { y ->
                Position(this.x + x, this.y + y)
            }
        }
    }
}
