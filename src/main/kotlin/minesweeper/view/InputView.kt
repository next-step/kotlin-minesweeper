package minesweeper.view

object InputView {

    fun inputDataFromConsole(type: InputType): String {
        return when(type) {
            InputType.HEIGHT -> inputData("높이를 입력하세요.")
            InputType.WIDTH -> inputData("너비를 입력하세요.")
            InputType.MINE_COUNT -> inputData("지뢰는 몇 개인가요?")
        }
    }

    private fun inputData(typeMessage: String): String {
        println(typeMessage)
        return readln()
    }
}