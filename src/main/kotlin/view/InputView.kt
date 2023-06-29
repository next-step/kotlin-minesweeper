package view

class InputView {

    fun inputHeight(): Int {
        return readlnOrNull()?.toIntOrNull() ?: 0
    }

    fun inputWidth(): Int {
        return readlnOrNull()?.toIntOrNull() ?: 0
    }

    fun inputMineCount(): Int {
        return readlnOrNull()?.toIntOrNull() ?: 0
    }
}
