package minesweeper.domain

@JvmInline
value class Lands(val elements: List<Land>) {
    fun openSurroundings(point: Point) {
        val surroundings = Direction.applyTo(point)
        elements.filter { it.point in surroundings }.forEach { it.open() }
    }

    fun hasUnopenedLand(): Boolean = elements.any { !it.isOpened() }

    fun find(point: Point): Land? = elements.firstOrNull { it.point == point }
}
