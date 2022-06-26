package dto

import domain.Coordinate
import domain.LandMineRandomMap
import domain.Line

class LandMineMapUIRequest(val mapCoordinates: List<Line>, val landMineCoordinates: List<Coordinate>) {

    constructor(landMineRandomMap: LandMineRandomMap) : this(
        landMineRandomMap.mapLine.reversed(),
        landMineRandomMap.landMineCoordinates
    )
}
