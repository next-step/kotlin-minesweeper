package domain

class Mines(private val minePositions: Set<MinePosition>) {
    fun contains(height: Int, width: Int): Boolean {
        return minePositions.contains(MinePosition(height, width))
    }
}
