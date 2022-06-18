import domain.Board
import domain.MineGenerator
import domain.Mines
import view.InputView
import view.PrintView

class MineController(
    height: Int,
    width: Int,
    mine: Int,
    mineGenerator: MineGenerator
) {

    private val board: Board

    init {
        val mines = makeMinePosition(mine, mineGenerator)

        board = Board(height, width, mines)
    }

    fun run() {
        while (true) {
            val openPosition = InputView.getPositionWithPositionDesc()

            val isGameOver = board.isMineAt(openPosition.row, openPosition.col)

            if (isGameOver) {
                PrintView.printGameOver()
                break
            }

            board.openAt(openPosition.row, openPosition.col)

            PrintView.printMineBoard(board)
        }
    }

    private fun makeMinePosition(mineCount: Int, mineGenerator: MineGenerator): Mines {
        return Mines(
            List(mineCount) {
                mineGenerator.generateMine()
            }.toSet()
        )
    }
}
