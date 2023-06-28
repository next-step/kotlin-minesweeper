package minesweeper.domain

data class Sequence(val value: Int) {
    companion object {
        fun generate(maxNotContains: Int, count: Int): List<Sequence> =
            (0 until maxNotContains)
                .shuffled()
                .subList(0, count)
                .sorted()
                .map { Sequence(it) }
    }
}

class SequenceConverter(val sizeOfLine: Int) {
    fun sequence(point: Point): Sequence = Sequence(point.x + (sizeOfLine * point.y))
    fun point(sequence: Sequence): Point = Point(sequence.value % sizeOfLine, sequence.value / sizeOfLine)
}
