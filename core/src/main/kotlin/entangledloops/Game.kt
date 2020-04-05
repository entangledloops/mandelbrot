package com.entangledloops

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxInputAdapter
import kotlin.math.absoluteValue

class Game : KtxGame<Screen>() {
    // render vars
    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture
    var imgX = 0f
    var imgY = 0f

    // mandelbrot vars
    var power = 2
    var iterations = 20
    var cutoff = 2
    var min = -2.0
    var max = 2.0

    override fun create() {
        batch = SpriteBatch()
        img = buildMandelbrotTexture()

        Gdx.input.inputProcessor = object : KtxInputAdapter {
            override fun keyDown(keycode: Int): Boolean {
                when (keycode) {
                    Input.Keys.ESCAPE -> Gdx.app.exit()
                    Input.Keys.LEFT -> if (iterations > 1) --iterations
                    Input.Keys.RIGHT -> ++iterations
                    Input.Keys.DOWN -> if (power > 1) --power
                    Input.Keys.UP -> ++power
                    else -> return false
                }
                println("iterations: $iterations, power: $power, min: $min, max: $max")
                img.dispose()
                img = buildMandelbrotTexture()
                return true
            }
        }
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with (batch) {
            begin()
            draw(img, imgX, imgY)
            end()
        }
    }

    override fun dispose() {
        img.dispose()
        batch.dispose()
    }

    fun buildMandelbrotTexture(): Texture {
        val range = (max - min).absoluteValue

        fun getPixel(x: Int, y: Int): Int {
            val projX = min + ((x.toDouble()/(Gdx.graphics.width-1)) * range)
            val projY = min + ((y.toDouble()/(Gdx.graphics.height-1)) * range)
            val c = Complex(-1.037, 0.17)
            var xn = Complex(projX, projY)
            for (i in 0 until iterations) {
                val prevSeed = xn
                repeat (power-1) { xn *= prevSeed }
                xn += c
                if (xn.mag() > cutoff) return ((255*(i/iterations.toDouble())).toByte().toInt() shl 24) or 0xFF
            }
            return 0xFF0000FF.toInt()
        }

        val pixmap = Pixmap(Gdx.graphics.width, Gdx.graphics.height, Pixmap.Format.RGBA8888).apply {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    pixels.putInt(getPixel(x, y))
                }
            }
            pixels.rewind()
        }

        return Texture(pixmap)
    }
}
