class Board(val board: MutableList<MutableList<Cell>>) {

    init {
        validate()
    }

    private fun validate() {
        validateEmpty()
        validateLineSizes()
    }

    private fun validateLineSizes() {
        val line = board.first()
        val referenceSize = line.size
        val isAllLineSizeEqual = board.all { it.size == referenceSize }

        require(isAllLineSizeEqual) { ERR_MSG_LINE_SIZE_DIFFERS }
    }

    private fun validateEmpty() {
        require(board.isNotEmpty()) { ERR_MSG_EMPTY_LIST }
    }

    fun settingMine(number: Int) {
        val rowIndex = getSelectRowIndex(number)
        val columnIndex = getSelectColumIndex(number)

        setMine(rowIndex, columnIndex)
    }

    private fun setMine(rowIndex: Int, columnIndex: Int) {
        board[rowIndex][columnIndex] = Mine
    }

    private fun getSelectColumIndex(number: Int): Int {
        val width = board.first().size
        return when (val columnIndex = number % width) {
            0 -> width - INDEX_OFFSET
            else -> columnIndex - INDEX_OFFSET
        }
    }

    private fun getSelectRowIndex(number: Int): Int {
        val height = board.size
        return number / height - INDEX_OFFSET
    }

    companion object {
        private const val ERR_MSG_EMPTY_LIST = "맵이 비어있습니다."
        private const val ERR_MSG_LINE_SIZE_DIFFERS = "라인의 크기가 다릅니다."
        private const val INDEX_OFFSET = 1
    }
}
