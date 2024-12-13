package minesweeper.domain

data class Height(val value: Int) {
    constructor(column: String) : this(column.toIntOrNull() ?: throw IllegalArgumentException("ERROR_INVALID"))

    init {
        require(value > 0) { ERROR_INVALID }
    }

    companion object {
        private const val ERROR_INVALID = "잘못된 값입니다"
    }
}
