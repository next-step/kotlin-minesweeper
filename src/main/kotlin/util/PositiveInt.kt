package util

@JvmInline
value class PositiveInt(private val int: Int) {
    init {
        require(int >= 0) { "좌표는 0이상이어야 합니다." }
    }
}
