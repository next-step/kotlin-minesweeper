package domain.coordinategenerator

import domain.Coordinate

internal class FakeCoordinatesGenerator : CoordinatesGenerator {
    override fun generate() = emptySet<Coordinate>()
}
