package domain

/**
 * 좌표 저장을 위한 클래스
 * Created by Jaesungchi on 2022.06.28..
 */
data class Position(val x: Int, val y: Int) {
    init {
        if (x < ZERO_POSITION)
            throw IllegalArgumentException(POSITION_CAN_NOT_UNDER_ZERO)
        if (y < ZERO_POSITION)
            throw IllegalArgumentException(POSITION_CAN_NOT_UNDER_ZERO)
    }

    companion object {
        private const val ZERO_POSITION = 0
        private const val POSITION_CAN_NOT_UNDER_ZERO = "위치는 0보다 작을 수 없습니다."
    }
}
