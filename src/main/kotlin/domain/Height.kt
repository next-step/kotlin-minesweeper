package domain
data class Height(val value: Int) {
    init {
        require(value > 0) { "value must be greater than 0." }
    }
}
