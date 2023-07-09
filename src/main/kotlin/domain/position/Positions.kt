package domain.position

import domain.AroundMineCount

fun List<Position>.toPositions() = Positions(this)
fun Positions.filtered(condition: (position: Position) -> Boolean): Positions = value.filter { condition(it) }.toPositions()
fun Positions.contains(position: Position): Boolean = value.contains(position)
data class Positions(val value: List<Position>) {
    fun getAroundMineCount(minePositions: Positions): AroundMineCount {
        return value.count { minePositions.value.contains(it) }.run {
            AroundMineCount(this)
        }
    }
}
