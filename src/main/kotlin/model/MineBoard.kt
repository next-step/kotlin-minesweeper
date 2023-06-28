package model

class MineBoard(elements: Map<Position, MineMark>) {

    init {
        require(
            (0..elements.keys.maxOf { it.x }).flatMap { x ->
                (0..elements.keys.maxOf { it.y }).map { y -> Position(x, y) }
            }.all { elements.containsKey(it) }
        ) {
            "elements must be filled with all positions. but provided `$elements`"
        }
    }

    val elements: Map<Position, MineMark> = elements.toMap()
    val size = elements.size
    val maxXPosition get() = elements.keys.maxOf { it.x }
    val maxYPosition get() = elements.keys.maxOf { it.y }

    fun contains(position: Position): Boolean {
        return elements.containsKey(position)
    }

    fun isEqualMarkInPosition(position: Position, mark: MineMark): Boolean {
        validateContainsPosition(position)
        return elements[position] == mark
    }

    fun replacedMark(position: Position, mark: MineMark): MineBoard {
        validateContainsPosition(position)
        return MineBoard(elements.toMutableMap().apply { this[position] = mark })
    }

    private fun validateContainsPosition(position: Position) {
        if (contains(position).not()) {
            throw IllegalArgumentException("position must be in elements. elements($this), position(`$position`)")
        }
    }

    override fun toString(): String {
        return "MineBoard(elements=$elements, size=$size)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MineBoard

        return elements == other.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }
}
