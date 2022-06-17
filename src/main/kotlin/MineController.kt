import domain.Board
import domain.MineGenerator
import domain.Mines
import view.InputView

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
        val openPosition = InputView.getPositionWithPositionDesc()
    }

    private fun makeMinePosition(mineCount: Int, mineGenerator: MineGenerator): Mines {
        return Mines(
            List(mineCount) {
                mineGenerator.generateMine()
            }.toSet()
        )
    }
}
