package minesweeper.domain

fun interface MinePositionSelector {
    fun generate(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): Set<Position>
}
