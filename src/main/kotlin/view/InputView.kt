package view

import domain.Number

class InputView {
    fun inputHeight(): Number {
        return inputRequireNumber("높이를 입력하세요.")
    }

    fun inputWidth(): Number {
        return inputRequireNumber("너비를 입력하세요.")
    }

    fun inputMineCount(): Number {
        return inputRequireNumber("지뢰는 몇 개인가요?")
    }

    private fun inputRequireNumber(message: String): Number {
        println(message)
        return runCatching { Number(readln()) }
            .fold(
                onSuccess = {
                    println()
                    return it
                },
                onFailure = { e ->
                    println(e.message)
                    inputRequireNumber(message)
                }
            )
    }
}
