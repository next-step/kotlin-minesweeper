import domain.CoordinateRandomGenerator
import domain.LandMineRandomMap
import dto.CoordinateGeneratorRequest
import dto.LandMineMapRequest
import view.InputView
import view.LandMineMapUI

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val landmine = InputView.landmine()

    val landMineMapRequest = LandMineMapRequest.of(height, width)
    val coordinateGeneratorRequest = CoordinateGeneratorRequest.of(landMineMapRequest.width, landMineMapRequest.height, landmine)
    val landMineRandomMap = LandMineRandomMap(landMineMapRequest, CoordinateRandomGenerator(coordinateGeneratorRequest))

    val mapUI = LandMineMapUI(landMineRandomMap)
    mapUI.title()
    mapUI.map()
}
