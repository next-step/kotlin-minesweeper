package model

class Block(var status: MineStatus = MineStatus.block) {
    var mineCount: Int = 0

    fun changeToMine() {
        this.status = MineStatus.mine
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
