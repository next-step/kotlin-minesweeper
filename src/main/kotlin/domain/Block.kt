package domain

interface Block {
    val symbol: String
    fun isMine(): Boolean
    fun setMineCount()
}
