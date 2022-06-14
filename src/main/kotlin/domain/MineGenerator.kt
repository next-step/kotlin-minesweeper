package domain

interface MineGenerator {
    fun generateMine(height: Int, width: Int): MinePosition
}
