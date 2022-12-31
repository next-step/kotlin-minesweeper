package domain

fun coordinateOf(x: Int, y: Int): Coordinate {
    return Coordinate(Number(x), Number(y))
}

fun coordinateListOf(vararg coordinates: Pair<Int, Int>): List<Coordinate> {
    return coordinates.map {
        Coordinate(Number(it.first), Number(it.second))
    }
}

fun mineCellListOf(vararg coordinates: Pair<Int, Int>): List<Mine> {
    return coordinates.map {
        Mine(Coordinate(Number(it.first), Number(it.second)))
    }
}

fun blankCellListOf(vararg coordinates: Pair<Int, Int>): List<Blank> {
    return coordinates.map {
        Blank(Coordinate(Number(it.first), Number(it.second)))
    }
}

fun locationsOf(vararg value: Int): Locations {
    return Locations(value.map { it })
}

fun openBlankCellListOf(vararg coordinates: Pair<Int, Int>): List<Blank> {
    return coordinates.map {
        Blank(Coordinate(Number(it.first), Number(it.second)), Status.OPEN)
    }
}
