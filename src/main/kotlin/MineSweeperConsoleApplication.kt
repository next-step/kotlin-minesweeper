fun main() {
    println("높이를 입력하세요.")
    val inputHeight: String = readln()
    require(inputHeight.toIntOrNull() != null) { "높이는 정수 값을 입력해야 합니다." }

    println("너비를 입력하세요.")
    val inputWidth: String = readln()
    require(inputWidth.toIntOrNull() != null) { "너비는 정수 값을 입력해야 합니다." }

    println("지뢰는 몇 개인가요?")
    val inputMineCount: String = readln()
    require(inputMineCount.toIntOrNull() != null) { "지뢰 개수는 정수 값을 입력해야 합니다." }
}
