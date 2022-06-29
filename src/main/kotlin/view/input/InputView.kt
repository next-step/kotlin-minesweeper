package view.input

object InputView {
    fun <T> receiveUserInput(userInputRequest: UserInputRequest<T>): T {
        println(userInputRequest.message)
        return userInputRequest.inputConverter.convert(readlnOrNull()).also {
            println()
        }
    }
}
