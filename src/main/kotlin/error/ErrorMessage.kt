package error

enum class ErrorMessage(val message: String) {

    WRONG_INPUT_MESSAGE("잘못된 입력입니다."),
    EMPTY_INPUT_MESSAGE("입력값이 없습니다."),
    EXPECT_NUMBER_MESSAGE("숫자를 입력해주세요."),
    EXPECT_POSITIVE_NUMBER_HEIGHT("높이는 0보다 커야 합니다."),
    EXPECT_POSITIVE_NUMBER_WIDTH("너비는 0보다 커야 합니다."),
    EXPECT_MINE_COUNT_LESS_THAN_BOARD_SIZE("지뢰의 개수는 전체 칸의 개수보다 작아야 합니다.")
}
