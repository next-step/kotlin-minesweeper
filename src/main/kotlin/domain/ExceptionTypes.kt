package domain

object ExceptionTypes {
    const val INPUT_HEIGHT_MUST_NOT_NULL_OR_EMPTY = "높이값은 비어서는 안됩니다."
    const val INPUT_WIDTH_MUST_NOT_NULL_OR_EMPTY = "너비값은 비어서는 안됩니다."
    const val INPUT_NUMBER_OF_MINES_MUST_NOT_NULL_OR_EMPTY = "지뢰 갯수 값은 비어서는 안됩니다."
    const val INPUT_MUST_INT = "입력 값은 정수여야 합니다."
    const val INPUT_NUMBER_OF_MINES_MUST_NOT_OVER_LIMIT = "지뢰 갯수는 최개 갯수 %d를 초과 할 수 없습니다."
    const val SLOT_CHECK_REQUEST_NOT_OVER_SIZE = "위치 정보 확인 요청은 최대 크기를 넘길 수 없습니다."
}
