package minesweeper.views

object InputView {

    tailrec fun askHeight(): Int {
        println(HEIGHT_QUESTION)
        return readLine()?.toInt() ?: askHeight()
    }

    tailrec fun askWidht(): Int {
        println(WIDTH_QUESTION)
        return readLine()?.toInt() ?: askWidht()
    }

    tailrec fun askMine(): Int {
        println(MINE_QUESTION)
        return readLine()?.toInt() ?: askWidht()
    }

    private const val HEIGHT_QUESTION = "높이를 입력하세요."
    private const val WIDTH_QUESTION = "너비를 입력하세요."
    private const val MINE_QUESTION = "지뢰는 몇 개인가요?"
}
