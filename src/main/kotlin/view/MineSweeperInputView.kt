package view

object MineSweeperInputView {
    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getNumberOfMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun getOpenInput(): Pair<Int, Int> {
        print("open: ")
        val split = readln().split(",")
        require(split.size == 2) { "Invalid input" }
        return Pair(split[0].toInt(), split[1].toInt())
    }
}
