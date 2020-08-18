import view.ResultView

sealed class Result<out R> {

    data class Success<out T>(val content: T) : Result<T>()

    data class Failure(val exception: Exception) : Result<Nothing>()
}

fun <T> Result<T>.check() =
    when (this) {
        is Result.Success -> this.content
        is Result.Failure -> {
            ResultView.printError(this.exception)
            null
        }
    }
