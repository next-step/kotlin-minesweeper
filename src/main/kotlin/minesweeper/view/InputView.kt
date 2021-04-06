package minesweeper.view

object InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("잘못된 정보 입니다.")
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("잘못된 정보 입니다.")
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()?.toInt() ?: throw IllegalArgumentException("잘못된 정보 입니다.")
    }
}
