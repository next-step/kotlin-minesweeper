object RandomGenerator {
    fun points(width: Int, height: Int, count: Int): List<Point> {
        require(width > 0) { "너비는 0보다 커야합니다. 입력값: $width" }
        require(height > 0) { "높이는 0보다 커야합니다. 입력값: $height" }
        require(count >= 0 && count <= width * height) { "0개 이상 ${width * height}개 이하의 좌표를 생성할 수 있습니다. 입력값: $count" }

        val points = (0 until width * height).shuffled().map {
            val x = it % width
            val y = it / width
            Point.from(x, y)
        }.take(count)
        return points
    }
}
