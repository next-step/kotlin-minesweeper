package view

object InputView {
    private fun input(print: String): Int {
        println(print)
        return readln().toInt()
    }

    fun inputHeight(): Int = input("높이를 입력해주세요.")
    fun inputWidth(): Int = input("너비를 입력해주세요.")
    fun inputMineCount(): Int = input("지뢰개수를 입력해주세요.")
}
