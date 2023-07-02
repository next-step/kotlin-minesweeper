package domain

fun List<Position>.toPositions() = Positions(this)
data class Positions(val value: List<Position>)
