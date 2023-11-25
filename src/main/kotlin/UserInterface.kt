interface UserInterface {
    fun askHeight(): Int
    fun askWidth(): Int
    fun askMineCount(): Int
    fun printStartAnnouncement()
    fun printMinefieldMatrix(minefieldMatrix: List<List<Int>>)
}
