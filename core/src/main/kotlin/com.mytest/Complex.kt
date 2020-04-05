package com.mytest

import kotlin.math.sqrt

class Complex(val a: Double, val b: Double)
{
    override fun toString(): String = "$a + ${b}i"

    operator fun unaryMinus() = Complex(-a, -b)
    operator fun plus(c: Complex) = Complex(a + c.a, b + c.b)
    operator fun minus(c: Complex) = Complex(a - c.a, b - c.b)
    operator fun times(c: Complex) = Complex((a * c.a) + (b * c.b * -1), (a * c.b + b * c.a))
    operator fun div(c: Complex): Complex {
        val conj = c.conj()
        val num = this * conj
        val den = c * conj
        return Complex(num.a / den.a, num.b / den.a)
    }

    fun conj(): Complex = Complex(a, -b)
    fun mag(): Double = sqrt(a*a + b*b)
}
