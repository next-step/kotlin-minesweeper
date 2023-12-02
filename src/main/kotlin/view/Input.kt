package view

fun inputHeight(): Int {
    println("높이를 입력하세요.")

    return inputToInt(readln())
}

fun inputWidth(): Int {
    println("너비를 입력하세요.")

    return inputToInt(readln())
}

fun inputMineNum(): Int {
    println("지뢰는 몇 개인가요?")

    return inputToInt(readln())
}

fun inputOpenPosition(): Pair<Int, Int> {
    println("open: ")

    val input = readln()
    val (x, y) = tokenizeInts(input)

    return Pair(x, y)
}
