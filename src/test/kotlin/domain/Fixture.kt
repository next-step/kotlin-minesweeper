package domain

fun coordinateOf(x: Int, y: Int): Coordinate {
    return Coordinate(Row(x), Column(y))
}

fun coordinateListOf(vararg coordinates: Pair<Int, Int>): List<Coordinate> {
    return coordinates.map {
        Coordinate(Row(it.first), Column(it.second))
    }
}

fun mineCellListOf(vararg coordinates: Pair<Int, Int>): List<Mine> {
    return coordinates.map {
        Mine(Coordinate(Row(it.first), Column(it.second)))
    }
}

fun blankCellListOf(vararg coordinates: Pair<Int, Int>): List<Blank> {
    return coordinates.map {
        Blank(Coordinate(Row(it.first), Column(it.second)))
    }
}

fun locationsOf(vararg value: Int): Locations {
    return Locations(value.map { it })
}
