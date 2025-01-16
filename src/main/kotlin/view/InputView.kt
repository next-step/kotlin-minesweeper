package view

object InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun getMineNumber(): Int {
        println("지뢰는 몇 개 인가요?")
        val mineNumber = readln().toInt()
        println("지뢰찾기 게임 시작")
        return mineNumber
    }

    fun getCoordinate(): Pair<Int, Int> {
        print("open: ")
        val input = readln().split(", ")
        return Pair(input[0].toInt(), input[1].toInt())
    }
}
