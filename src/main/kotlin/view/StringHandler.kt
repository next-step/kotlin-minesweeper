package view

fun inputToInt(input: String): Int {
    require(input.isNotBlank()) { "빈 값이 입력되었습니다." }

    val num = input.toIntOrNull()
    require(num != null) { "숫자만 입력되어야 합니다." }
    require(num > 0) { "입력값은 양의 정수여야 합니다." }

    return num
}
