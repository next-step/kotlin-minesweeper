package domain

fun interface MinePositionGenerator {
    fun generate(height: Int, width: Int, mineCount: Int): List<Position>
}
