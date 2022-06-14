import domain.BoardInfo
import domain.MinePosition
import domain.Mines
import view.InputView
import view.PrintView
import kotlin.random.Random

class MineController {

    fun run() {
        val height = getHeightFromUser()
        val width = getWidthFromUser()
        val mine = getMineCountFromUser()
        val mines = makeMinePosition(height, width, mine)

        val boardInfo = BoardInfo(height,width, mines)

        PrintView.printMineBoard(boardInfo)
    }

    private fun makeMinePosition(height: Int, width: Int, mineCount: Int): Mines {
        return Mines(List(mineCount) {
            val row = Random.nextInt(height)
            val col = Random.nextInt(width)
            MinePosition(row, col)
        }.toSet())
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
