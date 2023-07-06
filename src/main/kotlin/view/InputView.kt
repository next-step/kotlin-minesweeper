package view

object InputView {

    private const val HEIGHT_STRING = "높이를 입력하세요."
    private const val WIDTH_STRING = "\n너비를 입력하세요."
    private const val COUNT_STRING = "\n지뢰는 몇 개인가요?"

    fun getHeight(): Int {
        println(HEIGHT_STRING)
        return readln().toInt()
    }

    fun getWidth(): Int {
        println(WIDTH_STRING)
        return readln().toInt()
    }

    fun getMineCounts(): Int {
        println(COUNT_STRING)
        return readln().toInt()
    }
}
