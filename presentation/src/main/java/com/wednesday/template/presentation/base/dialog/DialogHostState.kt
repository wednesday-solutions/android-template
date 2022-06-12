package com.wednesday.template.presentation.base.dialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.wednesday.template.presentation.base.effect.ShowAlertDialogEffect
import kotlinx.coroutines.sync.Mutex

class DialogHostState {

    private val mutex = Mutex()

    var currentDialogData by mutableStateOf<ShowAlertDialogEffect?>(null)
        private set

    suspend fun showDialog(dialogData: ShowAlertDialogEffect) {
        mutex.lock()
        currentDialogData = dialogData
    }

    fun dismissDialog() {
        if (currentDialogData != null) {
            mutex.unlock()
            currentDialogData = null
        }
    }
}
