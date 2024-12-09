package domain

fun interface MineGenerator {
    fun getMine(
        width: Int,
        height: Int,
        numberofMine: Int,
    ): Set<Mine>
}
