package constants

/**
 * Created by Jaesungchi on 2022.06.28..
 */
object Messages {
    const val WRITE_HEIGHT = "높이를 입력하세요."
    const val WRITE_WIDTH = "너비를 입력하세요."
    const val WRITE_MINE_COUNT = "지뢰는 몇 개인가요?"
    const val START_MINE_SWEEPER = "지뢰찾기 게임 시작"

    const val MINE = "M "
    const val NOT_MINE = "O "
}

object ErrorMessages {
    const val IS_NOT_NUMBER = "숫자가 아닙니다."
    const val IS_UNDER_ZERO = "숫자가 0보다 작습니다."
    const val IS_NULL_OR_EMPTY = "입력된 값이 없습니다."
    const val POSITION_CAN_NOT_UNDER_ZERO = "위치는 0보다 작을 수 없습니다."
}
