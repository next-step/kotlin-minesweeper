import map.Board
import ramdom.RandomInterface

class MineSearch(val board: Board, private val randomLogic: RandomInterface) {

    fun settingMines(count: Int) {
        val maxValue = board.getBoardMaxValue()
        validateMine(count)

        val positions = randomLogic.createRandomNumList(count, maxValue)

        positions.forEach { board.settingMine(it) }
    }

    private fun validateMine(count: Int) {
        require(board.getBoardMaxValue() <= count) { ERR_MSG_MINE_OVERFLOW }
    }

    companion object {
        private const val ERR_MSG_MINE_OVERFLOW = "보드의 크기보다 지뢰가 더 많습니다."
    }
}
