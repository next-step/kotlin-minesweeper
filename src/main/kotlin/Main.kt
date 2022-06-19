import domain.MineAllocator
import domain.Minesweeper
import domain.MinesweeperProperty
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val minesweeperWidth = inputView.enterMinesweeperWidth()
    val minesweeperHeight = inputView.enterMinesweeperHeight()
    val mineCount = inputView.enterMineCount()

    val mineAllocator = MineAllocator()
    val minesweeper = Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocator)

    resultView.printMinesweeperBoard(minesweeper)
}
