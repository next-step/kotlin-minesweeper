package view

object Input {

    fun getLine() = readlnOrNull() ?: throw IllegalArgumentException("입력하여 주세요.")
}
