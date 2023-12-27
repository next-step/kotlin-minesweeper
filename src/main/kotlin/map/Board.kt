package map

import ramdom.MineRandomLogic
import ramdom.RandomInterface

class Board(private val mapInfo: MapInfo) {

    val mineBoard: MutableList<MutableList<Cell>>

    init {
        mineBoard = createBoard()
        settingBoard()
    }

    fun open(position: Position) {
        mineBoard[position.y][position.x].openCell()
    }

    fun getAroundPosition(position: Position): List<Position> {
        val aroundPosition = mutableListOf<Position>()
        val searchingPosition = mineBoard[position.y][position.x].searchAround(mapInfo.height, mapInfo.width)
            .filter { mineBoard[it.y][it.x] is None && !mineBoard[it.y][it.x].isOpen() }

        aroundPosition.addAll(searchingPosition)

        return aroundPosition
    }

    private fun settingBoard() {
        settingMines(mapInfo.mineCnt)
        settingMineCntInfo(mapInfo)
    }

    private fun createBoard(): MutableList<MutableList<Cell>> {
        val height = mapInfo.height
        val width = mapInfo.width
        return MutableList(height) { y -> MutableList(width) { x -> None(Position(y, x)) } }
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
        mineBoard[rowIndex][columnIndex] = Mine()
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
        for (y in INDEX_ZERO until mapInfo.height) {
            setMineCntInfoRow(mapInfo, y)
        }
    }

    private fun setMineCntInfoRow(mapInfo: MapInfo, y: Int) {
        for (x in INDEX_ZERO until mapInfo.width) {
            setMineCntInfo(y, x)
        }
    }

    private fun setMineCntInfo(y: Int, x: Int) {
        val cell = mineBoard[y][x]
        if (cell is None) {
            val mineCnt = getMineCnt(cell)
            cell.mineCnt = mineCnt
        }
    }

    private fun getMineCnt(cell: None): Int {
        var mineCnt = 0

        for (around in cell.searchAround(mapInfo.height, mapInfo.width)) {
            mineCnt = increaseMineCnt(mineCnt, around.y, around.x)
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
