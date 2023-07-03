package next.step.minesweeper.utils

fun <T> retryOnFailure(command: () -> T, onFailure: (Throwable) -> Unit): T {
    return runCatching {
        command()
    }.fold(
        onSuccess = { it },
        onFailure = {
            onFailure(it)
            retryOnFailure(command, onFailure)
        },
    )
}
