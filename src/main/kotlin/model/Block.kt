package model

class Block(var status: MineStatus = MineStatus.block) {
    var mineCount: Int = 0

    fun changeToMine() {
        this.status = MineStatus.mine
    }

    fun setMineCount(x: Int, y: Int, minePlate: MinePlate) {
        val startXPosition = x - 1
        val endXPosition = x + 1
        val startYPosition = y - 1
        val endYPosition = y + 1

        for (dy in startYPosition..endYPosition) {
            for (dx in startXPosition..endXPosition) {
                setMineCountValue(minePlate, dy, dx)
            }
        }
    }

    private fun setMineCountValue(minePlate: MinePlate, dy: Int, dx: Int) {
        try {
            if(minePlate.value[dy].column.blocks[dx] == this) {
                return
            }
            if (minePlate.value[dy].column.blocks[dx].isMine()) {
                mineCount++
            }
        } catch (ex: IndexOutOfBoundsException) {
            return
        }
    }

    fun isBlock(): Boolean {
        return this.status == MineStatus.block
    }

    fun isMine(): Boolean {
        return this.status == MineStatus.mine
    }

    override fun toString(): String {
        return if (this.status == MineStatus.block) {
            mineCount.toString()
        } else {
            MINE
        }
    }

    companion object {
        const val BLOCK = "C"
        const val MINE = "*"
    }
}
