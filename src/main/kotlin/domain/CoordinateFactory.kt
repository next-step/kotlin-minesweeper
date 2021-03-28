package domain

object CoordinateFactory {

    fun create(maxX: Int, maxY: Int, nthNumber: Int): Coordinate {
        require(maxX > 0 && maxY > 0) { "최대좌표의 X,Y 는 0보다 커야 합니다. maxX: $maxX, maxY: $maxY" }
        require(maxX * maxY >= nthNumber) { "총 좌표 개수보다 번째수가 더 클 수 없습니다. maxX: $maxX, maxY: $maxY, nthNumber: $nthNumber" }

        val x = (nthNumber - 1) % maxX + 1
        val y = (nthNumber - 1) / maxX + 1

        return Coordinate(x, y)
    }
}
