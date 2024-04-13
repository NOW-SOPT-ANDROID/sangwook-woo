package org.sopt.ui.orbit

import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.updateState(
    reducer: STATE.() -> STATE
) = intent {
    reduce { state.reducer() }
}