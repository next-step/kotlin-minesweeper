package domain

class NormalBlock : Block() {

    override var mineCount: Int = 0
    override val isMine: Boolean = false

    override fun increaseMineCount() {
        mineCount++
    }
}
