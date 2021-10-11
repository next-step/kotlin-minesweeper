package model.position

interface Position {
    fun indexPair(): Pair<Int, Int>

    fun aroundPositions(): List<Position>

    data class Fake(
        private val id: Int
    ) : Position {
        override fun indexPair(): Pair<Int, Int> {
            return Pair(0, 0)
        }

        override fun aroundPositions(): List<Position> {
            return emptyList()
        }
    }
}