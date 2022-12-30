package minesweeper

data class Cords(
    val mapCords: List<MapCord>
) {

    companion object {
        fun of(height: Int, width: Int): Cords {
            val xCords = 0 until width
            val yCords = 0 until height

            return Cords(
                MapCordBuilder()
                    .setX(xCords)
                    .flatMap { it.setY(yCords) }
                    .map { it.build() }
            )
        }

        fun from(centerMapCord: MapCord): Cords {
            return Cords(
                ShiftCord.values().mapNotNull {
                    centerMapCord + it
                }
            )
        }
    }
}
