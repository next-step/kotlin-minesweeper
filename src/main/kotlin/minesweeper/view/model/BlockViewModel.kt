package minesweeper.view.model

sealed interface BlockViewModel {

    override fun toString(): String
}

class HideBlockViewModel : BlockViewModel {

    override fun toString(): String = "□"
}

class CleanBlockViewModel(
    private val value: String
) : BlockViewModel {
    override fun toString(): String = value
}

class MineBlockViewModel : BlockViewModel {

    override fun toString(): String = "♣"
}
