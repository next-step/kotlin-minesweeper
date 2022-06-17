import domain.Board
import domain.MineGenerator
import domain.Mines
import view.PrintView

class MineController {

    fun run(
        height: Int,
        width: Int,
        mine: Int,
        mineGenerator: MineGenerator
    ) {
        val mines = makeMinePosition(mine, mineGenerator)

        val boardInfo = Board(height, width, mines)

//        PrintView.printMineBoard(boardInfo)
    }

    private fun makeMinePosition(mineCount: Int, mineGenerator: MineGenerator): Mines {
        return Mines(
            List(mineCount) {
                mineGenerator.generateMine()
            }.toSet()
        )
    }
}
