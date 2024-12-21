package minesweeper.domain

fun interface SpotGenerator {
    fun generate(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): List<Spot>
}
