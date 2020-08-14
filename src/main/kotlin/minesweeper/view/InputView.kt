package minesweeper.view

object InputView {
    fun inputLength(): Int {
        println("높이를 입력하세요.")
        return changeInt(readLine())
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return changeInt(readLine())
    }

    fun inputMines(): Int {
        println("지뢰는 몇 개인가요?")
        return changeInt(readLine())
    }

    private fun changeInt(inputValue: String?): Int {
        if (inputValue.isNullOrBlank()) {
            throw IllegalArgumentException("공백값과 null값은 받을수없습니다.")
        }
        try {
            return inputValue.toInt()
        } catch (e: Exception) {
            throw IllegalArgumentException("숫자 이외의 값을 입력하지 마세요.")
        }
    }
}
