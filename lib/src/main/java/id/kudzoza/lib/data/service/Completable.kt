package id.kudzoza.lib.data.service

import id.kudzoza.lib.data.model.Payload

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

class Completable<T>(
    private val payload: Payload<T>
) {
    fun execute(onSuccess: (T?) -> Unit, onFailed: (String) -> Unit) {
        if (payload.isSuccess)
            onSuccess.invoke(payload.data)
        else
            onFailed.invoke(payload.message)
    }

    fun execute(onDone: () -> Unit) {
        onDone.invoke()
    }
}