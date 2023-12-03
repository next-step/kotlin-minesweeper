import map.Board
import ramdom.RandomInterface

class MineSearch(val board: Board, private val randomLogic: RandomInterface) {
    fun settingMines(count: Int) {
        val maxValue = board.getBoardMaxValue()
        val positions = randomLogic.createRandomNumList(count, maxValue)

        positions.forEach { board.settingMine(it) }
    }
}
