package minesweeper.view

import minesweeper.domain.Cell

object ResultView {
    private const val BLANK = "C"
    private const val MINE = "*"
    private const val SPACE = " "

    enum class Message(val context: String) {
        REQUEST_HEIGHT("높이를 입력하세요."),
        REQUEST_WIDTH("너비를 입력하세요."),
        REQUEST_MINE("지뢰는 몇 개인가요?"),
        START("지뢰찾기 게임 시작");
    }

    fun printMessage(message: Message) = println(message.context)
    
}
