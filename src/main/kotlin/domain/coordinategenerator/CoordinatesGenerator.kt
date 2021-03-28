package domain.coordinategenerator

import domain.Coordinate

interface CoordinatesGenerator {
    fun generate(): Set<Coordinate>
}
