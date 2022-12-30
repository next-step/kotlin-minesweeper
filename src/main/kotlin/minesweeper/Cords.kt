package minesweeper

data class Cords(
    val cords: List<Cord>
) {

    companion object {
        fun of(height: Int, width: Int): Cords {
            val xCords = 0 until width
            val yCords = 0 until height

            return Cords(
                CordBuilder()
                    .setX(xCords)
                    .flatMap { it.setY(yCords) }
                    .map { it.build() }
            )
        }

        fun from(centerCord: Cord): Cords {
            return Cords(
                listOf(
                    centerCord + Cord(-1, -1),
                    centerCord + Cord(0, -1),
                    centerCord + Cord(1, -1),
                    centerCord + Cord(-1, 0),
                    centerCord + Cord(1, 0),
                    centerCord + Cord(-1, 1),
                    centerCord + Cord(0, 1),
                    centerCord + Cord(1, 1),
                )
            )
        }
    }
}
