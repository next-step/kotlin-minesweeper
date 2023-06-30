import kotlin.random.Random

object RandomGenerator {
    fun point(width: Int, height: Int): Point {
        require(width > 0) { "너비는 0보다 커야합니다" }
        require(height > 0) { "높이는 0보다 커야합니다" }
        val x = X(Random.nextInt(width))
        val y = Y(Random.nextInt(height))
        return Point(x, y)
    }
}
