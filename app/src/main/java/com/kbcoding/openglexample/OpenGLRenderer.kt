package com.kbcoding.openglexample

import android.opengl.GLES32.GL_COLOR_BUFFER_BIT
import android.opengl.GLES32.glClear
import android.opengl.GLES32.glClearColor
import android.opengl.GLES32.glViewport
import android.opengl.GLSurfaceView

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class OpenGLRenderer : GLSurfaceView.Renderer {

    override fun onDrawFrame(arg0: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(arg0: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(arg0: GL10?, arg1: EGLConfig?) {
        glClearColor(0f, 1f, 0f, 1f)
    }
}