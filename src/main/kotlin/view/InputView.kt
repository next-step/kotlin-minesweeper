package view

object InputView {

    fun height(): String {
        println("높이를 입력하세요.")
        return readlnOrNull() ?: throw IllegalArgumentException()
    }

    fun width(): String {
        println("너비를 입력하세요.")
        return readlnOrNull() ?: throw IllegalArgumentException()
    }

    fun landmine(): String {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull() ?: throw IllegalArgumentException()
    }
}
