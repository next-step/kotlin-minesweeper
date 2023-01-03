package minesweeper.domain

data class MapCords(
    val mapCords: List<MapCord>
) {

    companion object {
        fun of(height: Int, width: Int): MapCords {
            val xCords = 0 until width
            val yCords = height - 1 downTo 0

            return MapCords(
                MapCordBuilder()
                    .setY(yCords)
                    .flatMap { it.setX(xCords) }
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
