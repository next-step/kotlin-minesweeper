import domain.Map
import view.InputView
import view.OutView

fun main() {
    val map = createMap()
    OutView.printMap(map)
}

private fun createMap(): Map {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()
    return Map.of(height = height, width = width, mineCount)
}
