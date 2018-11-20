package com.ebsoft.watchlist.ui.base

import android.arch.lifecycle.ViewModel
import com.ebsoft.watchlist.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

/**
 * Created by endre on 07/09/18.
 */

abstract class BaseViewModel<N>(val dataManager: DataManager) : ViewModel(), CoroutineScope {

    private var mNavigator: WeakReference<N>? = null

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    var navigator: N
        get() = mNavigator?.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        job.cancel()
        super.onCleared()
    }
}
