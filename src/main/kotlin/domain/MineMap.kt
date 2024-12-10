package domain

class MineMap(val map: Map<MinePosition, Boolean>) {
    fun isMine(
        x: Int,
        y: Int,
    ): Boolean {
        return map.getOrDefault(MinePosition(x, y), false)
    }
}
