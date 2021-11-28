package mine.cell

/**
 * 셀의 위치 정보 관리
 * */
class Position(val x: Int, val y: Int) {
    init {
        require(x >= MIN_POSITION && y >= MIN_POSITION)
    }

    companion object {
        const val MIN_POSITION = 0
    }
}
