interface UserInterface {
    fun askHeight(): Int
    fun askWidth(): Int
    fun askMineCount(): Int
    fun printStartAnnouncement()
    fun printGameBoard(gameBoard: List<List<Int>>)
}
