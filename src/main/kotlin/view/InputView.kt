package view

fun getHeightOfField(): Int {
    println("높이를 입력하세요.")
    return readLineFromConsole().toInt()
}

fun getWidthOfField(): Int {
    println("너비를 입력하세요.")
    return readLineFromConsole().toInt()
}

fun getMinesCount(): Int {
    println("지뢰는 몇개 인가요?")
    return readLineFromConsole().toInt()
}

private fun readLineFromConsole() = readLine()
    ?: throw IllegalStateException("콘솔 값을 읽을 수 없습니다.")
