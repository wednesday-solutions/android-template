package com.wednesday.template.presentation.base.effect

enum class EffectResult {
    /**
     * Indicates that the effect has been handled.
     */
    HANDLED,

    /**
     * Indicates that the effect has not been handled.
     */
    UNHANDLED;

    val isUnhandled: Boolean
        get() = this == UNHANDLED
}
