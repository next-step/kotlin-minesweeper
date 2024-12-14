package minsweeper.domain

class AroundMineCountJudge {

    fun judge(
        boardSize: BoardSize,
        position: Int,
        minePositions: List<Int>,
    ): Int = position.findAroundPositions(boardSize)
        .matches(minePositions)

    private fun Int.findAroundPositions(boardSize: BoardSize): List<Int> {
        val column = this % boardSize.width
        val row = this / boardSize.width

        val aroundPositions = mutableListOf<Int>()

        // 왼쪽
        if (column > 0) {
            aroundPositions.add("${row}${column - 1}".toInt())
        }

        // 오른쪽
        if (column < boardSize.width - 1) {
            aroundPositions.add("${row}${column + 1}".toInt())
        }

        // 가운데 위
        if (row > 0) {
            aroundPositions.add("${row - 1}${column}".toInt())
        }

        // 가운데 아래
        if (row < boardSize.height - 1) {
            aroundPositions.add("${row + 1}${column}".toInt())
        }

        // 왼쪽 위
        if (column > 0 && row > 0) {
            aroundPositions.add("${row - 1}${column - 1}".toInt())
        }

        // 오른쪽 위
        if (column < boardSize.width - 1 && row > 0) {
            aroundPositions.add("${row - 1}${column + 1}".toInt())
        }

        // 왼쪽 아래
        if (column > 0 && row < boardSize.height - 1) {
            aroundPositions.add("${row + 1}${column - 1}".toInt())
        }

        // 오른쪽 아래
        if (column < boardSize.width - 1 && row < boardSize.height - 1) {
            aroundPositions.add("${row + 1}${column + 1}".toInt())
        }

        return aroundPositions.toList()
    }

    private fun List<Int>.matches(minePositions: List<Int>): Int = this.intersect(minePositions.toSet()).size

}
