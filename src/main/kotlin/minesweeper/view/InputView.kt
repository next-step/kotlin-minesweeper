package minesweeper.view

object InputView {

    fun getHeight(): String {
        println("높이를 입력하세요.")
        return readLine()!!
    }

    fun getWidth(): String {
        println("너비를 입력하세요.")
        return readLine()!!
    }

    fun getMinCount(): String {
        println("지뢰는 몇 개인가요?")
        return readLine()!!
    }

    fun getPosition(): String {
        println("어느 위치를 여시겠습니까")
        return readLine()!!
    }
}
