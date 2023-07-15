package minesweeper.domain

/**
 * ### (x, y) 좌표계의 특정 위치를 표현합니다
 */
data class Position(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x must be greater than and equal to zero" }
        require(y >= 0) { "y must be greater than and equal to zero" }
    }

    /**
     * ### 현재 위치에서 탐색 가능한 8분면 위치를 추출합니다.
     */
    fun nearby(maxX: Int, maxY: Int): Positions {
        return Positions(
            OctantDelta.values()
                .filter { this.x + it.dx in 0 until maxX && this.y + it.dy in 0 until maxY }
                .map { Position(x = this.x + it.dx, y = this.y + it.dy) }
        )
    }
}

/**
 * ### Position 리스트를 delegate 시킨 일급 컬렉션 입니다
 */
class Positions(values: List<Position>) : List<Position> by values

/**
 * ### 특정 위치에서 8분면으로 탐색할 수 있는 좌표의 변화량을 정의한 Enum 입니다
 */
enum class OctantDelta(val dx: Int, val dy: Int) {
    UP(0, -1),
    UPPER_RIGHT(1, -1),
    RIGHT(1, 0),
    LOWER_RIGHT(1, 1),
    DOWN(0, 1),
    LOWER_LEFT(-1, 1),
    LEFT(-1, 0),
    UPPER_LEFT(-1, -1),
}
