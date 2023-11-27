package domain

fun interface MineGenerator {
    fun generate(count: Int): List<Mine>
}
