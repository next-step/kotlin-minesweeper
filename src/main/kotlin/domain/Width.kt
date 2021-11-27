package domain

data class Width(
    val value: Int,
) {
    init {
        require(value > 0) { "넓이는 0보다 커야합니다" }
    }
}
