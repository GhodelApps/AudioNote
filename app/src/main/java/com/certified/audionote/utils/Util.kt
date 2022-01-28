/*
 * Copyright (c) 2021 Samson Achiaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.certified.audionote.utils

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils
import com.vmadalin.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*

val colors = listOf(
    -504764, -740056, -1544140, -2277816, -3246217, -4024195,
    -4224594, -7305542, -7551917, -7583749, -10712898, -10896368, -10965321,
    -11419154, -14654801
)

fun filePath(activity: Activity): String? {
//    val file = File()
    return activity.getExternalFilesDir("/")?.absolutePath
}

fun hasPermission(context: Context, permission: String) =
    EasyPermissions.hasPermissions(context, permission)

fun requestPermission(activity: Activity, message: String, requestCode: Int, permission: String) {
    EasyPermissions.requestPermissions(activity, message, requestCode, permission)
}

fun currentDate(): Calendar {
    return Calendar.getInstance()
}

fun formatDate(date: Long): String {
    val dateString = DateUtils.getRelativeTimeSpanString(
        date,
        currentDate().timeInMillis,
        DateUtils.SECOND_IN_MILLIS
    ).toString()
    return when {
        "minute" in dateString -> {
            SimpleDateFormat("h:mm a", Locale.getDefault()).format(date)
        }
        " seconds" in dateString -> {
            "now"
        }
        else -> formatSimpleDate(date)
    }
}

fun formatReminderDate(date: Long): String =
    SimpleDateFormat("dd MMM, yyyy h:mm a", Locale.getDefault()).format(date)

fun formatSimpleDate(date: Long): String =
    SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(date)

fun formatDateOnly(date: Long): String =
    SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(date)

fun formatTime(date: Long): String = SimpleDateFormat("h:mm a", Locale.getDefault()).format(date)