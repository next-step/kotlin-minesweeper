import domain.Board
import domain.MineGenerator
import domain.Mines
import view.InputView
import view.PrintView

class MineController(private val mineGenerator: MineGenerator) {

    fun run() {
        val height = getHeightFromUser()
        val width = getWidthFromUser()
        val mine = getMineCountFromUser()
        val mines = makeMinePosition(height, width, mine)

        val boardInfo = Board(height, width, mines)

        PrintView.printMineBoard(boardInfo)
    }

    private fun makeMinePosition(height: Int, width: Int, mineCount: Int): Mines {
        return Mines(
            List(mineCount) {
                mineGenerator.generateMine(height, width)
            }.toSet()
        )
    }

    private fun getHeightFromUser(): Int {
        PrintView.printHeightMessage()

        return InputView.getIntOrThrow()
    }

    private fun getWidthFromUser(): Int {
        PrintView.printWidthMessage()

        return InputView.getIntOrThrow()
    }

    private fun getMineCountFromUser(): Int {
        PrintView.printMineMessage()

        return InputView.getIntOrThrow()
    }
}
