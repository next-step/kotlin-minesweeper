class Board(width: Int, height: Int, private val generator: MineGenerator) {

    val board: Map<Dot, DotState>

    init {
        board = (1..width).map {
            createPoint(it, height)
        }.flatten().toMap()
    }

    private fun createPoint(width: Int, height: Int): List<Pair<Dot, DotState>> {
        return (1..height).map {
            Dot(width, it) to generator.generate()
        }
    }


}
