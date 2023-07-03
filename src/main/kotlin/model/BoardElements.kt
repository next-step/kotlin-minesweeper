package model

import model.minemark.MineMark

class BoardElements(elements: Collection<BoardElement>) {

    val elements: Collection<BoardElement> = elements.toSet()

    val size: Int by lazy { elements.size }
    private val positions = elements.map { it.position }
    val maxXPosition: Int by lazy { positions.maxOf { it.x } }
    val maxYPosition: Int by lazy { positions.maxOf { it.y } }

    operator fun contains(position: Position): Boolean {
        return elements.any { it.position == position }
    }

    fun contains(position: Position, mark: MineMark): Boolean {
        return elements.any { it.position == position && it.mineMark == mark }
    }

    fun marksMap(markMapper: (Position, MineMark) -> MineMark): BoardElements {
        return BoardElements(
            elements.map {
                BoardElement(it.position, markMapper(it.position, it.mineMark), it.openStatus)
            }
        )
    }

    fun replacedPositionMark(position: Position, mineMark: MineMark): BoardElements {
        return BoardElements(
            elements.map {
                if (it.position == position) {
                    BoardElement(it.position, mineMark, it.openStatus)
                } else {
                    it
                }
            }
        )
    }

    fun doesNotContainsMark(mark: MineMark): Boolean {
        return elements.all { it.mineMark != mark }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardElements

        return elements == other.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }

    override fun toString(): String {
        return "BoardElements(elements=$elements)"
    }
}
