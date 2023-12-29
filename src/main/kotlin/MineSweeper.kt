
class MineSweeper(
    private val width: Int,
    private val height: Int,
) {
    fun cells(): Map<Position, String> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y->
                Position(y, x) to "C"
            }
        }.toMap()
    }
}
