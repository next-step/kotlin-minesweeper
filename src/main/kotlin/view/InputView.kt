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

    tailrec fun inputMine(): Int {
        println("지뢰는 몇 개 인가요?")
        val mine = readLine()?.toIntOrNull()
        return if (mine != null) {
            mine
        } else {
            println("지뢰는 숫자만 입력 가능합니다.")
            inputMine()
        }
    }
}
