package model

import model.minemark.MineMark

class FilledElements(elements: Map<Position, MineMark>) {

    init {
        require(isAllFilled(elements)) {
            "elements must be filled with all positions. but provided `$elements`"
        }
    }

    val elements: Map<Position, MineMark> = elements.toMap()
    val size = elements.size
    val maxXPosition: Int by lazy { positions(elements).maxOf { it.x } }
    val maxYPosition: Int by lazy { positions(elements).maxOf { it.y } }
    val isClosedMineCountAny: Boolean by lazy { filteredMineCountElements(elements).any { !it.isOpened } }
    val isClosedMineAll: Boolean by lazy { filteredMineElements(elements).all { !it.isOpened } }

    private fun filteredMineCountElements(elements: Map<Position, MineMark>): Collection<MineMark> {
        return marks(elements).filter { it.isMineCount }
    }

    private fun filteredMineElements(elements: Map<Position, MineMark>): Collection<MineMark> {
        return marks(elements).filter { it.isMine }
    }

    fun contains(position: Position): Boolean {
        return elements.containsKey(position)
    }

    fun doesNotContainsMark(mark: MineMark): Boolean {
        return marks(elements).contains(mark).not()
    }

    fun replacedMarkElements(mapper: (Position, MineMark) -> MineMark): FilledElements {
        return FilledElements(elements.mapValues { (position, mark) -> mapper(position, mark) })
    }

    fun count(predicate: (Position, MineMark) -> Boolean): Int {
        return elements.entries.count {
            predicate(it.key, it.value)
        }
    }

    private fun marks(elements: Map<Position, MineMark>): Collection<MineMark> {
        return elements.values
    }

    private fun isAllFilled(elements: Map<Position, MineMark>): Boolean {
        return zeroToRange(positions(elements).maxOf { it.x })
            .flatMap { x -> positions(x, positions(elements).maxOf { it.y }) }
            .all { elements.containsKey(it) }
    }

    private fun positions(elements: Map<Position, MineMark>): Collection<Position> {
        return elements.keys
    }

    private fun positions(x: Int, maxY: Int): Collection<Position> {
        return zeroToRange(maxY)
            .map { y -> Position(x, y) }
    }

    fun mark(position: Position): MineMark {
        validateContainsPosition(position)
        return elements[position]!!
    }

    private fun zeroToRange(count: Int): IntRange = (0..count)

    private fun validateContainsPosition(position: Position) {
        if (!elements.containsKey(position)) {
            throw IllegalArgumentException("position must be in elements. elements($this), position(`$position`)")
        }
    }

    override fun toString(): String {
        return "MineBoard(elements=$elements, size=$size)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FilledElements

        return elements == other.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }
}
