package view

object InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("입력 값은 null 값이 올 수 없습니다")
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("입력 값은 null 값이 올 수 없습니다")
    }

    fun inputMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("입력 값은 null 값이 올 수 없습니다")
    }

}