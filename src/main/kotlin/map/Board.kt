package map

import ramdom.RandomInterface

class Board(private val mapInfo: MapInfo, private val randomLogic: RandomInterface) {

    val board: MutableList<MutableList<Cell>>

    init {
        board = createBoard(mapInfo)
        settingMines(mapInfo.mineCnt)
    }

    private fun createBoard(mapInfo: MapInfo): MutableList<MutableList<Cell>> {
        val height = mapInfo.height
        val width = mapInfo.width
        return MutableList(height) { MutableList(width) { None } }
    }

    private fun settingMines(count: Int) {
        val maxValue = getBoardMaxValue()

        val positions = randomLogic.createRandomNumList(count, maxValue)

        positions.forEach { settingMine(it) }
    }

    private fun settingMine(position: Int) {
        val rowIndex = getSelectRowIndex(position)
        val columnIndex = getSelectColumIndex(position)

        setMine(rowIndex, columnIndex)
    }

    private fun setMine(rowIndex: Int, columnIndex: Int) {
        board[rowIndex][columnIndex] = Mine
    }

    private fun getBoardMaxValue(): Int {
        val height = mapInfo.height
        val width = mapInfo.width

        return height * width - INDEX_OFFSET
    }

    // 지뢰 로직 버그
    private fun getSelectColumIndex(number: Int): Int {
        if (number == 0) return 0
        val height = mapInfo.height

        return number % height
    }

    private fun getSelectRowIndex(number: Int): Int {
        val width = mapInfo.width
        return when (val columnIndex = number / width) {
            0 -> 0
            else -> columnIndex
        }
    }

    companion object {
        private const val INDEX_OFFSET = 1
    }
}
