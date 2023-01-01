package minesweeper.domain

data class MapCords(
    val mapCords: List<MapCord>
) {

    companion object {
        fun of(height: Int, width: Int): MapCords {
            val xCords = 0 until width
            val yCords = 0 until height

            return MapCords(
                MapCordBuilder()
                    .setX(xCords)
                    .flatMap { it.setY(yCords) }
                    .map { it.build() }
            )
        }

        fun from(centerMapCord: MapCord): MapCords {
            return MapCords(
                ShiftCord.values().mapNotNull {
                    centerMapCord + it
                }
            )
        }
    }
}
