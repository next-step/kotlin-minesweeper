class MineSweeper(
    private val width: Int,
    private val height: Int,
    private val mineCount: Int
) {
    init {
        require(width > 0) { INVALID_VALUE }
        require(height > 0)
        require(mineCount > 0)
    }

    fun initialize(): List<List<String>> {
        val initialList = (1..(height * width - mineCount)).map {
            "C"
        }
        val mineList = (1..mineCount).map {
            "*"
        }
        val mineSweeperList = (initialList + mineList).shuffled()
        return getResultList(mineSweeperList)
    }

    private fun getResultList(initialList: List<String>): List<List<String>> {
        return (0 until height).map { height ->
            val line = height * width
            initialList.slice(line until line + width)
        }
    }

    companion object {
        private const val INVALID_VALUE = "잘못된 값을 입력하셨습니다."
    }
}
