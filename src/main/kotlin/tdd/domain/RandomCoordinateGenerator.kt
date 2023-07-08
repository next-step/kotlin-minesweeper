package tdd.domain

fun randomCoordinates(height: Int, width: Int): (Int) -> Set<Coordinate> {
    return fun(count: Int): Set<Coordinate> {
        return Coordinates.all(height, width)
            .shuffled()
            .take(count)
            .toSet()
    }
}
