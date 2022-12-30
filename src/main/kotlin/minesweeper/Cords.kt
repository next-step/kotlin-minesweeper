package minesweeper

class Cords(
    val cords: List<Pair<Int, Int>>
) {

    companion object {
        fun of(height: Int, width: Int): Cords {
            return Cords(
                (0 until height).map { y ->
                    (0 until width).map { x -> (y to x) }
                }.flatten()
            )
        }
    }
}
