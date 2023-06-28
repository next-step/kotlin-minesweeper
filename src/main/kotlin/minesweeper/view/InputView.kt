package minesweeper.view

object InputView {

    fun inputData(type: InputType): String {
        return when(type) {
            InputType.HEIGHT -> {
                println("높이를 입력하세요.")
                readln()
            }
            InputType.WIDTH -> {
                println("너비를 입력하세요.")
                readln()
            }
            InputType.MINE_COUNT -> {
                println("지뢰는 몇 개인가요?")
                readln()
            }
        }

    }
}