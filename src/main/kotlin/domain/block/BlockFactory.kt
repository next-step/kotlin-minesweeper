package domain.block

object BlockFactory {

    fun create(isMine: Boolean): Block {
        return if (isMine) Mine() else Nothing()
    }
}
