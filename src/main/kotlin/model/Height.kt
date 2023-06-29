package model

@JvmInline
value class Height(val value: Int) {
    init {
        require(value >= 0) { throw IllegalArgumentException("높이 값은 0 이상이어야합니다.") }
    }
}
