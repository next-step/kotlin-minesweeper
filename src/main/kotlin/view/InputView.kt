package view

object InputView {
    tailrec fun inputHeight(): Int {
        println("높이를 입력하세요.")
        val height = readLine()?.toIntOrNull()
        return if (height != null) {
            height
        } else {
            println("높이는 숫자만 입력할 수 있습니다.")
            inputHeight()
        }
    }
}
