package com.kbcoding.openglexample

import android.content.Context
import android.opengl.GLES32
import com.kbcoding.openglexample.FileUtils.readTextFromRaw

object ShaderUtils {
    fun createProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
        val programId = GLES32.glCreateProgram()
        if (programId == 0) {
            return 0
        }
        GLES32.glAttachShader(programId, vertexShaderId)
        GLES32.glAttachShader(programId, fragmentShaderId)
        GLES32.glLinkProgram(programId)
        val linkStatus = IntArray(1)
        GLES32.glGetProgramiv(programId, GLES32.GL_LINK_STATUS, linkStatus, 0)
        if (linkStatus[0] == 0) {
            GLES32.glDeleteProgram(programId)
            return 0
        }
        return programId
    }

    fun createShader(context: Context?, type: Int, shaderRawId: Int): Int {
        val shaderText = readTextFromRaw(context!!, shaderRawId)
        return createShader(type, shaderText)
    }

    fun createShader(type: Int, shaderText: String?): Int {
        val shaderId = GLES32.glCreateShader(type)
        if (shaderId == 0) {
            return 0
        }
        GLES32.glShaderSource(shaderId, shaderText)
        GLES32.glCompileShader(shaderId)
        val compileStatus = IntArray(1)
        GLES32.glGetShaderiv(shaderId, GLES32.GL_COMPILE_STATUS, compileStatus, 0)
        if (compileStatus[0] == 0) {
            GLES32.glDeleteShader(shaderId)
            return 0
        }
        return shaderId
    }
}