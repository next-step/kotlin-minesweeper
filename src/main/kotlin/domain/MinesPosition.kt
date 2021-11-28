package domain

class MinesPosition(
    private val value: List<Position>,
) {

    val size = value.size

    operator fun contains(position: Position): Boolean {
        return value.contains(position)
    }
}
