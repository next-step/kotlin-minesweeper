class MineMap(val board: MutableList<MutableList<Cell>>) {

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

    companion object{
        private const val ERR_MSG_EMPTY_LIST = "맵이 비어있습니다."
        private const val ERR_MSG_LINE_SIZE_DIFFERS = "라인의 크기가 다릅니다."
    }
}
