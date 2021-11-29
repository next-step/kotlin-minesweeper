package domain

@JvmInline
value class Height(
    val value: Int,
) {
    init {
        require(value > 0) { "높이는 0보다 커야합니다." }
    }
}
