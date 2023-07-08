package domain

data class MineCount(val value: Int) {
    init {
        require(value >= 0) { "value must be positive number." }
    }
}
