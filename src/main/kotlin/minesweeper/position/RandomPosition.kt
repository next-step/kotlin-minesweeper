package minesweeper.position

import minesweeper.board.BoardElement

class RandomPosition(
    element: BoardElement
) : PositionGenerate {

    private val rowRange = IntRange(0, element.height - 1)
    private val colRange = IntRange(0, element.width - 1)

    override fun generate(): Position =
        Position(rowRange.random(), colRange.random())

    fun generate(count: Int): Set<Position> = loopGenerate(count, mutableSetOf())

    private tailrec fun loopGenerate(count: Int, set: MutableSet<Position>): MutableSet<Position> =
        when (set.size < count) {
            true -> loopGenerate(count, set.apply { set.add(generate()) })
            false -> set
        }
}
