object CreateFactory {
    fun createMineMap(width: Int, height: Int): MineMap {
        return MineMap(MutableList(height) { MutableList(width) { None } })
    }
}
