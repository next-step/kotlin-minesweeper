import map.MineMap
import map.position.selector.RandomMinePositionSelector
import model.Height
import model.MineCount
import model.Width
import view.InputView
import view.OutputView

class MineSweeper(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()
        val mineMap = MineMap(
            MineMap.Property(
                Height(height),
                Width(width),
                MineCount(mineCount),
            ),
            RandomMinePositionSelector((0 until height), (0 until width))
        )
        outputView.drawMineMap(mineMap.getMapSnapShot())
    }
}
