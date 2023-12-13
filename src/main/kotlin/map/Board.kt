package map

import ramdom.RandomInterface

class Board(private val mapInfo: MapInfo, private val randomLogic: RandomInterface) {

    val mineBoard: MutableList<MutableList<Cell>>

    init {
        mineBoard = createBoard(mapInfo)
        settingBoard(mapInfo)
    }

    private fun settingBoard(mapInfo: MapInfo) {
        settingMines(mapInfo.mineCnt)
        settingOpen(mapInfo)
    }

    private fun createBoard(mapInfo: MapInfo): MutableList<MutableList<Cell>> {
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

    private fun settingOpen(mapInfo: MapInfo) {
        for (x in INDEX_ZERO until mapInfo.height) {
            setOpenRow(mapInfo, x)
        }
    }

    private fun setOpenRow(mapInfo: MapInfo, x: Int) {
        for (y in INDEX_ZERO until mapInfo.width) {
            setOpen(x, y)
        }
    }

    private fun setOpen(x: Int, y: Int) {
        val cell = mineBoard[x][y]
        if (cell is None) {
            val mineCnt = getMineCnt(cell)
            mineBoard[x][y] = Open(mineCnt)
        }
    }

    private fun getMineCnt(cell: None): Int {

        val cellX = cell.x
        val cellY = cell.y
        var mineCnt = 0

        for (addIndex in RelativeDirection.values()) {
            val newX = cellX + addIndex.x
            val newY = cellY + addIndex.y

            mineCnt = increaseMineCnt(mineCnt, newX, newY)
        }

        return mineCnt
    }

    private fun increaseMineCnt(mineCnt: Int, newX: Int, newY: Int): Int {
        if (!checkIndex(newX, newY)) return mineCnt
        return if (mineBoard[newX][newY] is Mine) mineCnt + 1 else mineCnt
    }

    private fun checkIndex(newX: Int, newY: Int): Boolean {
        return newX >= INDEX_ZERO && newX < mapInfo.width && newY >= INDEX_ZERO && newY < mapInfo.height
    }

    companion object {
        private const val INDEX_OFFSET = 1
        private const val INDEX_ZERO = 0
    }
}
