package com.example.testapp.util

import android.view.View


class Utils() {



}


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}