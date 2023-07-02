@JvmInline
value class X(val value: Int) {
    init {
        require(value >= 0) {
            "x 좌표는 0 이상의 값만 허용됩니다. 입력값: $value"
        }
    }
}
