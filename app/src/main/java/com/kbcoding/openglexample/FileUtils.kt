package com.kbcoding.openglexample

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object FileUtils {
    @JvmStatic
    fun readTextFromRaw(context: Context, resourceId: Int): String {
        val stringBuilder = StringBuilder()
        try {
            var bufferedReader: BufferedReader? = null
            try {
                val inputStream = context.resources.openRawResource(resourceId)
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                    stringBuilder.append("\r\n")
                }
            } finally {
                bufferedReader?.close()
            }
        } catch (ioex: IOException) {
            ioex.printStackTrace()
        } catch (nfex: Resources.NotFoundException) {
            nfex.printStackTrace()
        }
        return stringBuilder.toString()
    }
}