package br.com.esapiens.geocoder_library

import android.location.Location
import org.jdeferred.DeferredManager
import org.jdeferred.Promise
import org.jdeferred.android.AndroidDeferredManager
import org.jdeferred.impl.DeferredObject
import kotlin.concurrent.thread

/**
 * Created by esapiens on 21/08/17.
 */
class PromiseLocaleRetriever(private val localeRetriever: LocaleRetriever, private val deferredManager: DeferredManager = AndroidDeferredManager()) {

    constructor(localeRetriever: LocaleRetriever): this(localeRetriever, AndroidDeferredManager())

    fun findWithGoogleApi(location: SimpleLocation): Promise<String, Throwable, Void> {
        return createPromise {
            localeRetriever.findWithGoogleApi(location)
        }
    }

    fun findWithCoreApi(location: SimpleLocation): Promise<String, Throwable, Void> {
        return createPromise {
            localeRetriever.findWithCoreApi(location)
        }
    }

    fun find(firstMethod: LocationRetrievalMethod, location: SimpleLocation): Promise<String, Throwable, Void> {
        return createPromise {
            localeRetriever.find(firstMethod, location)
        }
    }

    private fun createPromise(action: () -> String): Promise<String, Throwable, Void> {
        val deferredObject = DeferredObject<String, Throwable, Void>()
        thread {
            try {
                val locationFound = action()
                deferredObject.resolve(locationFound)
            }
            catch (exception: Exception) {
                deferredObject.reject(exception)
            }
        }
        return deferredManager.`when`(deferredObject.promise())
    }
}