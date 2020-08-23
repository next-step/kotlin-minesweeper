package minesweeper.domain

val POSITION_REGULAR_EXPRESSION = "^(\\d{1,2},)+\\d{1,2}$".toRegex()

data class Position(val x: XPosition, val y: YPosition) {

    fun getNeighboringValue(direction: Direction, boardSize: BoardSize): Int {
        return when (direction) {
            Direction.UP -> maxOf(y.value - 1, 0)
            Direction.DOWN -> minOf(y.value + 1, boardSize.height.length - 1)
            Direction.LEFT -> maxOf(x.value - 1, 0)
            Direction.RIGHT -> minOf(x.value + 1, boardSize.width.length - 1)
        }
    }

    companion object {
        fun requestPosition(positionString: String, boardSize: BoardSize = BoardSize()): PositionCheckResult {
            if (!POSITION_REGULAR_EXPRESSION.matches(positionString)) return PositionCheckResult.InvalidateExpression
            val numbers: List<String> = positionString.split(",")
            val x = numbers[0].toInt()
            val y = numbers[1].toInt()
            if (x !in MIN_SIZE..boardSize.width.length) return PositionCheckResult.InvalidateY(boardSize)
            if (y !in MIN_SIZE..boardSize.height.length) return PositionCheckResult.InvalidateX(boardSize)
            return PositionCheckResult.Success(positionString, boardSize)
        }

        fun from(positionString: String): Position {
            val numbers: List<String> = positionString.split(",")
            val x = numbers[0].toInt()
            val y = numbers[1].toInt()
            return Position(XPosition.of(x), YPosition.of(y))
        }

        // 숫자 자체로 값이 들어올땐, 실제 리스트의 위치 값이므로 각각에 +1 을 해줘야함
        fun of(x: Int, y: Int): Position {
            return Position(XPosition.of(x + 1), YPosition.of(y + 1))
        }
    }
}
