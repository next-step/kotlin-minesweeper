package domain

data class Mine(val minePosition: MinePosition) {
    constructor(x: Int, y: Int) : this(MinePosition(x, y))

    fun getXPosition(): Int {
        return minePosition.x
    }

    fun getYPosition(): Int {
        return minePosition.y
    }
}
