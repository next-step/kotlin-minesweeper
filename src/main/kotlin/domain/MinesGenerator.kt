package domain

fun interface MinesGenerator {
    fun getMines(
        width: Int,
        height: Int,
        numberOfMine: Int,
    ): MineSet
}
