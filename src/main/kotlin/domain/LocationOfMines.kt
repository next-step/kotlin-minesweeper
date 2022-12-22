package domain

class LocationOfMines(
    private val mines: List<Coordinate>
) {
    fun exist(coordinate: Coordinate) = mines.contains(coordinate)

    fun countByNearMines(coordinate: Coordinate): Int {
        // TODO: -1, 0, 1만 가진 상대 좌표 (Relative?)로 리펙토링 및 Pos 제약조건 개선 2022/12/23 (koi)
        val relativeOfCoords = listOf(
            Coordinate(-1, -1),
            Coordinate(-1, 0),
            Coordinate(-1, 1),
            Coordinate(0, -1),
            Coordinate(0, 1),
            Coordinate(1, -1),
            Coordinate(1, 0),
            Coordinate(1, 1)
        )

        return relativeOfCoords.count { coordinate.isPossiblePlus(it) && exist(coordinate + it) }
    }
}
