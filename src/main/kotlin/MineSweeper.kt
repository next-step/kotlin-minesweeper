import domain.LandMineRandomMap
import dto.CoordinateGeneratorRequest
import dto.LandMineMapRequest
import dto.LandMineMapUIRequest
import view.InputView
import view.LandMineMapUI

object MineSweeper {

    fun run(inputView: InputView) {
        val height = inputView.height()
        val width = inputView.width()
        val landmine = inputView.landmine()

        val landMineMapRequest = LandMineMapRequest.of(height, width, landmine)
        val coordinateGenerator = CoordinateGeneratorRequest.of(landMineMapRequest)
        val landMineRandomMap = LandMineRandomMap(landMineMapRequest, coordinateGenerator)

        val mapUI = LandMineMapUI(LandMineMapUIRequest(landMineRandomMap))
        mapUI.title()
        mapUI.map()
    }
}
