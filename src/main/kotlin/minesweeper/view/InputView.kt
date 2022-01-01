package minesweeper.view

object InputView {

    fun getHeight(): Int {
        println("높이를 입력하세요")
        return getInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요")
        return getInt()
    }

    fun getMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return getInt()
    }

    fun getAction(): String {
        print("action type (open or flag) : ")
        return getInput()
    }

    fun getPosition(): Position {
        print("position: ")
        val rowWithColumn = getInput().split(",").map { it.trim() }
        return Position(rowWithColumn[0].toInt(), rowWithColumn[1].toInt())
    }

    private fun getInt() = getInput().toInt()

    private fun getInput() = readLine()!!

    data class Position(val row: Int, val column: Int)
}
