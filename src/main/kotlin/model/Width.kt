package model

@JvmInline
value class Width(val value: Int) {
    init {
        require(value >= 0) { throw IllegalArgumentException("너비 값은 0 이상이어야합니다.") }
    }
}
