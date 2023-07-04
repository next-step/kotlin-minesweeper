package domain.model

@JvmInline
value class Y(val value: Int) {
    init {
        require(value >= 0) {
            "y 좌표는 0 이상의 값만 허용됩니다. 입력값: $value"
        }
    }
}
