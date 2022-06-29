class NonMine: Square {
    override fun isMine(): Boolean = false

    override fun display(): String = "C"
}
