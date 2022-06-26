import domain.CoordinateRandomGenerator
import domain.LandMineRandomMap
import dto.CoordinateGeneratorRequest
import dto.LandMineMapRequest
import dto.LandMineMapUIRequest
import view.InputView
import view.LandMineMapUI

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val landmine = InputView.landmine()

    val landMineMapRequest = LandMineMapRequest.of(height, width)
    val coordinateGeneratorRequest = CoordinateGeneratorRequest.of(landMineMapRequest, landmine)
    val landMineRandomMap = LandMineRandomMap(landMineMapRequest, CoordinateRandomGenerator(coordinateGeneratorRequest))

    val mapUI = LandMineMapUI(LandMineMapUIRequest(landMineRandomMap))
    mapUI.title()
    mapUI.map()
}
