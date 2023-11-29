package business

fun interface MinePointGenerator {
    fun generate(boardInfo: BoardInfo): List<Point>
}
