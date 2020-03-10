package com.xslczx.mvvmdemo.ext

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentTransaction
import com.xslczx.mvvmdemo.R

fun Activity?.addFragment(
    @IdRes id: Int = R.id.container,
    className: String,
    tag: String? = null,
    arguments: Bundle? = null,
    addToBackStack: Boolean = false
) {
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.beginTransaction()
        .apply {
            add(id, FragmentFactory.loadFragmentClass(classLoader, className), arguments, tag)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
}

fun Activity?.replaceFragment(
    @IdRes id: Int = R.id.container,
    className: String,
    tag: String? = null,
    arguments: Bundle? = null,
    addToBackStack: Boolean = false
) {
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.beginTransaction()
        .apply {
            replace(id, FragmentFactory.loadFragmentClass(classLoader, className), arguments, tag)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
}


/**
 * 移除栈顶的fragment
 */
fun AppCompatActivity.popBackStackImmediate(): Boolean {
    var handle = false
    if (supportFragmentManager.backStackEntryCount > 0) {
        handle = supportFragmentManager.popBackStackImmediate()
    }
    return handle
}

/**
 * 清空回退栈
 */
fun AppCompatActivity.clearFragmentStack() {
    for (i in 0 until supportFragmentManager.backStackEntryCount) {
        supportFragmentManager.popBackStack()
    }
}