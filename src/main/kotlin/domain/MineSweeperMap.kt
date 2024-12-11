package domain

class MineSweeperMap(val map: Map<MinePosition, Boolean>) {
    fun isMine(
        x: Int,
        y: Int,
    ): Boolean {
        return map.getOrDefault(MinePosition(x, y), false)
    }
}
