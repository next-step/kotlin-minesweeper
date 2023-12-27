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
            .filter { closeNoneCell(it) }

        aroundPosition.addAll(searchingPosition)

        return aroundPosition
    }

    fun validatePosition(position: Position): Boolean {
        // 1. 보드를 넘어가는 셀.
        if (!validateIndex(position)) {
            return false
        }

        // 2. 이미 오픈된 셀
        if (validateOpen(position)) {
            return false
        }

        return true
    }

    fun isMine(position: Position): Boolean {
        return mineBoard[position.y][position.x] is Mine
    }

    fun getMineCnt(): Int {
        return mapInfo.mineCnt
    }

    fun getCloseCellCnt(): Int {
        return mineBoard.sumOf { it.count { cell -> !cell.isOpen() } }
    }

    private fun validateOpen(position: Position): Boolean {
        return mineBoard[position.y][position.x].isOpen()
    }

    private fun validateIndex(position: Position): Boolean {
        return position.y >= INDEX_ZERO &&
            position.y < mapInfo.height &&
            position.x >= INDEX_ZERO &&
            position.x < mapInfo.width
    }

    private fun closeNoneCell(position: Position) =
        mineBoard[position.y][position.x] is None && !mineBoard[position.y][position.x].isOpen()

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
            val y = linearIndexToRowIndex(position)
            val x = linearIndexToColumIndex(position)
            setMine(y, x)
        }
    }

    private fun setMine(y: Int, x: Int) {
        mineBoard[y][x] = Mine()
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
