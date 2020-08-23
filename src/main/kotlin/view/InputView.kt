package view

object InputView {
    tailrec fun inputHeight(): Int {
        println("높이를 입력하세요.")
        val height = readLine()?.toIntOrNull()
        return if (height != null) {
            height
        } else {
            println("높이는 숫자만 입력할 수 있습니다.")
            inputHeight()
        }
    }

    tailrec fun inputWidth(): Int {
        println("너비를 입력하세요.")
        val weight = readLine()?.toIntOrNull()
        return if (weight != null) {
            weight
        } else {
            println("너비는 숫자만 입력할 수 있습니다.")
            inputWidth()
        }
    }

    tailrec fun inputMine(validateMineCount: (Int) -> Boolean): Int {
        println("지뢰는 몇 개 인가요?")
        val mine = readLine()?.toIntOrNull()
        return if (mine != null && validateMineCount(mine)) {
            mine
        } else {
            println("숫자만 입력 가능하고, 너비와 높이의 곱보다 작아야합니다.")
            inputMine(validateMineCount)
        }
    }
}
