import dto.LandMineMapRequest
import view.InputView

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val landmine = InputView.landmine()

    val landMineMapRequest = LandMineMapRequest.of(height, width, landmine)
}
