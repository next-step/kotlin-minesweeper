package map

import ramdom.MineRandomLogic
import ramdom.RandomInterface

class Board(private val mapInfo: MapInfo) {

    val mineBoard: MutableList<MutableList<Cell>>

    init {
        mineBoard = createBoard()
        settingBoard()
    }

    private fun settingBoard() {
        settingMines(mapInfo.mineCnt)
        settingMineCntInfo(mapInfo)
    }

    private fun createBoard(): MutableList<MutableList<Cell>> {
        val height = mapInfo.height
        val width = mapInfo.width
        return MutableList(height) { x -> MutableList(width) { y -> None(x, y) } }
    }

    private fun settingMines(count: Int) {
        val maxValue = getBoardMaxValue()
        val positions = randomLogic.createRandomNumList(count, maxValue)

        positions.forEach { position ->
            val rowIndex = linearIndexToRowIndex(position)
            val columnIndex = linearIndexToColumIndex(position)
            setMine(rowIndex, columnIndex)
        }
    }

    private fun setMine(rowIndex: Int, columnIndex: Int) {
        mineBoard[rowIndex][columnIndex] = Mine
    }

    private fun getBoardMaxValue(): Int {
        val height = mapInfo.height
        val width = mapInfo.width

        return height * width - INDEX_OFFSET
    }

    private fun linearIndexToColumIndex(number: Int): Int {
        if (number == INDEX_ZERO) return INDEX_ZERO
        val height = mapInfo.height

        return number % height
    }

    private fun linearIndexToRowIndex(number: Int): Int {
        val width = mapInfo.width
        return when (val columnIndex = number / width) {
            INDEX_ZERO -> INDEX_ZERO
            else -> columnIndex
        }
    }

    private fun settingMineCntInfo(mapInfo: MapInfo) {
        for (x in INDEX_ZERO until mapInfo.height) {
            setMineCntInfoRow(mapInfo, x)
        }
    }

    private fun setMineCntInfoRow(mapInfo: MapInfo, x: Int) {
        for (y in INDEX_ZERO until mapInfo.width) {
            setMineCntInfo(x, y)
        }
    }

    private fun setMineCntInfo(x: Int, y: Int) {
        val cell = mineBoard[x][y]
        if (cell is None) {
            val mineCnt = getMineCnt(cell)
            cell.mineCnt = mineCnt
        }
    }

    private fun getMineCnt(cell: None): Int {
        var mineCnt = 0

        for (around in cell.searchAround(mapInfo.width, mapInfo.height)) {
            mineCnt = increaseMineCnt(mineCnt, around.x, around.y)
        }

        return mineCnt
    }

    private fun increaseMineCnt(mineCnt: Int, newX: Int, newY: Int): Int {
        return if (mineBoard[newX][newY] is Mine) mineCnt + 1 else mineCnt
    }

    companion object {
        private const val INDEX_OFFSET = 1
        private const val INDEX_ZERO = 0
        var randomLogic: RandomInterface = MineRandomLogic()
    }
}
