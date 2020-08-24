package domain

class Mine : Block() {

    override var mineCount: Int = -1
    override val isMine: Boolean = true

    override fun increaseMineCount() {
        // do nothing
    }
}
