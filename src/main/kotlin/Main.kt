import map.Height
import map.Map
import map.Width
import mine.MineCount
import mine.MinePoints
import view.InputView
import view.ResultView

fun main() {
    val heightSize = InputView.inputHeight() ?: 0
    val widthSize = InputView.inputWidth() ?: 0
    val height = Height(size = heightSize)
    val width = Width(size = widthSize)

    val map = Map.create(height = height, width = width)

    val mineCount = MineCount(count = InputView.inputMineCount() ?: 0)

    ResultView.gameStart()
    val minePoints = MinePoints.create(heightSize = heightSize, widthSize = widthSize).take(mineCount)
    minePoints.forEach { map.placeMine(it) }

    ResultView.printMap(map.grid)
}
