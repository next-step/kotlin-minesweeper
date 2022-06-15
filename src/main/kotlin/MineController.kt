import domain.Board
import domain.Mines
import domain.RandomMineGenerator
import view.InputView
import view.PrintView

class MineController {

    private lateinit var mineGenerator : RandomMineGenerator

    fun run() {
        val height = getHeightFromUser()
        val width = getWidthFromUser()
        val mine = getMineCountFromUser()
        mineGenerator = RandomMineGenerator(height, width)

        val mines = makeMinePosition(mine)

        val boardInfo = Board(height, width, mines)

        PrintView.printMineBoard(boardInfo)
    }

    private fun makeMinePosition(mineCount: Int): Mines {


        return Mines(
            List(mineCount) {
                mineGenerator.generateMine()
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
