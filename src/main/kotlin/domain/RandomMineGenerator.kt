package domain

class RandomMineGenerator(private val width: Int, private val height: Int) : MineGenerator {
    override fun generate(count: Int): List<Mine> {
        require(count <= width * height) { "최대 ${width}x${height}개의 지뢰를 만들 수 있습니다." }
        val cols = (Coordinate.MIN_X until width).shuffled()
        val rows = (Coordinate.MIN_Y until height).shuffled()
        return List(count) { Mine(cols[it], rows[it]) }
    }
}
