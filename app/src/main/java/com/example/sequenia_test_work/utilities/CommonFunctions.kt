package com.example.sequenia_test_work.utilities

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object CommonFunctions {
    fun showToast(context: Context, text: String, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context, text, duration).show()

    fun showToast(context: Context, text: Int, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context, text, duration).show()

    fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    fun Double.round(scale: Int = 1, mode: RoundingMode = RoundingMode.CEILING): Double =
        BigDecimal(this).setScale(scale, mode).toDouble()

    inline fun <reified T> List<T>.toFilmPairs(): List<Pair<T, T?>> {
        val (listA, listB) = this.partition {
            this.indexOf(it) % 2 == 0
        }

        val temp: MutableList<T?> = listB.toTypedArray().toMutableList()
        if (listB.size < listA.size) temp.add(null)
        return listA.zip(temp)
    }

    fun String.capitalize(): String =
        this.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
}