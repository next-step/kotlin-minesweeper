package domain

class Board(width: Int, height: Int, private val generator: MineGenerator) {

    val board: Map<Dot, DotState>

    init {
        board = (1..width).map {
            createDot(it, height)
        }.flatten().toMap()
    }

    private fun createDot(width: Int, height: Int): List<Pair<Dot, DotState>> {
        return (1..height).map {
            Dot(width, it) to generator.generate()
        }
    }
}
