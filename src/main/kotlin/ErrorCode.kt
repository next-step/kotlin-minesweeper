enum class ErrorCode(val msg: String) {
    NOT_POSITIVE_NUMBER_ERROR("number 값이 양수가 아닙니다"),
    INVALID_MINE_COUNT_NUMBER_ERROR("지뢰의 수가 영역의 최대 개수를 초과합니다"),
    INVALID_AROUND_MINE_COUNT_ERROR("주변 지뢰의 수는 0을 포함하는 양의 정수입니다"),
    INVALID_POSITION_FROM_INPUT_ERROR("입력한 좌표의 형식이 올바르지 않습니다")
}
