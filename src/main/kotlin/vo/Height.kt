package vo

@JvmInline
value class Height(val value: Int) {

    init {
        require(value > 0) { "높이는 1 이상이어야 합니다" }
    }
}
