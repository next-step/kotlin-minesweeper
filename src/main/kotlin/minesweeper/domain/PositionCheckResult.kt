package minesweeper.domain

sealed class PositionCheckResult {
    data class Success(val positionString: String, val boardSize: BoardSize = BoardSize()) : PositionCheckResult()
    data class InvalidateX(val boardSize: BoardSize) : PositionCheckResult()
    data class InvalidateY(val boardSize: BoardSize) : PositionCheckResult()
    object InvalidateExpression : PositionCheckResult()
}
