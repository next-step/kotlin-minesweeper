package minesweeper.domain

@JvmInline
value class Lands(val elements: List<Land>) {
    fun openSurroundings(land: Land) {
        val surroundings = Direction.applyTo(land.point)
        elements.filter { it.point in surroundings }.forEach { it.open() }
    }
}
