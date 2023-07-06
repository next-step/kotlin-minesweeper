package minesweeper.domain.explorer

sealed class ExploreResult {
    object SuccessExplore : ExploreResult()

    object FailExplore : ExploreResult()
}
