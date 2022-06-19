package vo

@JvmInline
value class Point(private val value: Int) {

    init {
        require(value > 0) { "위치는 1 이상이어야 합니다" }
    }
}
