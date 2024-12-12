package minesweeper.domain

data class MineCount(val count: Int) {
    constructor(count: String, width: Width, height: Height) : this(getCount(count, width, height))

    init {
        require(count >= 0) { ERROR_INVALID }
    }

    companion object {
        private const val ERROR_INVALID = "잘못된 값입니다"

        private fun getCount(
            count: String,
            width: Width,
            height: Height,
        ): Int {
            val mineCount = count.toIntOrNull() ?: throw IllegalArgumentException(ERROR_INVALID)
            require(mineCount <= width.value * height.value) { ERROR_INVALID }
            return mineCount
        }
    }
}
