import minesweeper.GameBoard
import minesweeper.Height
import minesweeper.MineCount
import minesweeper.MineGenerator
import minesweeper.MinesweeperBoard
import minesweeper.RandomPosition
import minesweeper.Width
import view.Input
import view.Output

private const val INPUT_HEIGHT = "높이를 입력하세요."
private const val INPUT_WIDTH = "너비를 입력하세요."
private const val INPUT_MINES = "지뢰는 몇 개인가요?"
private const val OUTPUT_START_MINESWEEPER = "지뢰찾기 게임 시작"

fun main() {
    Output.printAny(INPUT_HEIGHT)
    val height: Height = Height(Input.getLine())

    Output.printAny(INPUT_WIDTH)
    val width: Width = Width(Input.getLine())

    Output.printAny(INPUT_MINES)
    val mineCount: MineCount = MineCount(Input.getLine())

    val gameBoard = GameBoard(height, width)
    val mines = MineGenerator(mineCount, RandomPosition(gameBoard)).generate()
    val minesweeperBoard = MinesweeperBoard(gameBoard, mines)

    Output.printAny(OUTPUT_START_MINESWEEPER)
    Output.printMinesweeperBoard(minesweeperBoard)
}
