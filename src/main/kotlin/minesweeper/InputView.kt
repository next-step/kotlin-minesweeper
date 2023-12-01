package minesweeper

object InputView {
    fun getMineMap(): MineMap {
        println("높이를 입력하세요.")
        val row = LineCount(readln().toInt())
        println("너비를 입력하세요.")
        val col = LineCount(readln().toInt())
        println("지뢰는 몇 개인가요?")
        val mineNum = MineCount(readln().toInt())

        return MineMap.create(MineMapInfo(row, col, mineNum))
    }
}
