package minesweeper.view

fun inputHeight(): Int {
    println("높이를 입력하세요.")
    return readln().toIntOrNull() ?: retryInputHeight()
}

fun inputWidth(): Int {
    println("너비를 입력하세요.")
    return readln().toIntOrNull() ?: retryInputWidth()
}

fun inputMineCount(): Int {
    println("지뢰는 몇 개인가요?")
    return readln().toIntOrNull() ?: retryInputMineCount()
}

fun inputCoordinate(): CoordinateView {
    print("open: ")
    return runCatching { CoordinateView.from(readln()) }
        .getOrNull() ?: retryInputCoordinate()
}

private fun retryInputHeight(): Int {
    println("높이는 숫자를 입력하여야합니다.")
    return inputHeight()
}

private fun retryInputWidth(): Int {
    println("너비는 숫자를 입력하여야합니다.")
    return inputWidth()
}

private fun retryInputMineCount(): Int {
    println("지뢰 갯수는 숫자를 입력하여야합니다.")
    return inputMineCount()
}

private fun retryInputCoordinate(): CoordinateView {
    println("정확한 좌표로 입력하여야 합니다.")
    return inputCoordinate()
}
