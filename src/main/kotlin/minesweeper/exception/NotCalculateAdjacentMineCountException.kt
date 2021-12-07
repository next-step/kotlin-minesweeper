package minesweeper.exception

class NotCalculateAdjacentMineCountException(element: String): RuntimeException() {

    override val message: String = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 주변 지뢰 개수를 계산할 수 없습니다"
    }
}
