package next.step.minesweeper.utils

import next.step.minesweeper.view.OutputView

fun retryOnFailure(command: () -> Unit) {
    runCatching {
        command()
    }.onFailure { e ->
        OutputView.showError(e.message)
        retryOnFailure(command)
    }
}
