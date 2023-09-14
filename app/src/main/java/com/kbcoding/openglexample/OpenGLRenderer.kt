package com.kbcoding.openglexample

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLES32
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGLRenderer(private val context: Context) : GLSurfaceView.Renderer {
    private var programId = 0
    private var vertexData: FloatBuffer? = null
    private var uColorLocation = 0
    private var aPositionLocation = 0

    init {
        prepareData()
    }

    override fun onSurfaceCreated(arg0: GL10, arg1: EGLConfig) {
        GLES32.glClearColor(0f, 0f, 0f, 1f)
        val vertexShaderId =
            ShaderUtils.createShader(context, GLES32.GL_VERTEX_SHADER, R.raw.vertex_shader)
        val fragmentShaderId =
            ShaderUtils.createShader(context, GLES32.GL_FRAGMENT_SHADER, R.raw.fragment_shader)
        programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId)
        GLES32.glUseProgram(programId)
        bindData()
    }

    override fun onSurfaceChanged(arg0: GL10, width: Int, height: Int) {
        GLES32.glViewport(0, 0, width, height)
    }

    private fun prepareData() {
        val vertices = floatArrayOf(
            // треугольник 1
            -0.9f, 0.8f, -0.9f, 0.2f, -0.5f, 0.8f,

            // треугольник 2
            -0.6f, 0.2f, -0.2f, 0.2f, -0.2f, 0.8f,

            // треугольник 3
            0.1f, 0.8f, 0.1f, 0.2f, 0.5f, 0.8f,

            // треугольник 4
            0.1f, 0.2f, 0.5f, 0.2f, 0.5f, 0.8f,

            // линия 1
            -0.7f, -0.1f, 0.7f, -0.1f,

            // линия 2
            -0.6f, -0.2f, 0.6f, -0.2f,

            // точка 1
            -0.5f, -0.3f,

            // точка 2
            0.0f, -0.3f,

            // точка 3
            0.5f, -0.3f,
        )
        vertexData = ByteBuffer
            .allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        vertexData?.put(vertices)
    }

    private fun bindData() {
        uColorLocation = GLES32.glGetUniformLocation(programId, "u_Color")
        GLES32.glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)
        aPositionLocation = GLES32.glGetAttribLocation(programId, "a_Position")
        vertexData?.position(0)
        GLES32.glVertexAttribPointer(
            aPositionLocation, 2, GLES32.GL_FLOAT,
            false, 0, vertexData
        )
        GLES32.glEnableVertexAttribArray(aPositionLocation)
    }

    override fun onDrawFrame(arg0: GL10) {
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT)
        GLES32.glLineWidth(12f)
        GLES32.glDrawArrays(GLES32.GL_TRIANGLES, 0, 12)
        GLES32.glDrawArrays(GLES32.GL_LINES, 12, 4)
        GLES32.glDrawArrays(GLES32.GL_POINTS, 16, 3)
    }
}