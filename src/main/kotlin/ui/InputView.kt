package ui

class InputView {
    var height: Int = 0
        private set
    var width: Int = 0
        private set
    var mineSize: Int = 0
        private set

    fun show() {
        println("높이를 입력하세요.")
        height = readln().toInt()

        println("너비를 입력하세요.")
        width = readln().toInt()

        println("지뢰는 몇 개인가요?")
        mineSize = readln().toInt()
    }
}
