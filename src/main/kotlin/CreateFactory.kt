object CreateFactory {
    fun createBoard(width: Int, height: Int): Board {
        return Board(MutableList(height) { MutableList(width) { None } })
    }
}
