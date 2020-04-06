# Mandelbrot Renderer

This is a simple fractal rendering program written in Kotlin. Here is a rendering of a Julia set seeded with `c = -1.037 + 0.17i`:

![Julia](https://github.com/entangledloops/mandelbrot/blob/master/img/julia.png)

The general difference between Julia and Mandelbrot sets is in their generation. With Julia sets, we leave `c` fixed and iterate an
equation like `z = z^2 + c`, using the `x` and `y` values of each pixel as real and imaginary parts of our initial `z`.
With a Mandelbrot set, we instead adjust `c` for each pixel, leaving our `x` and `y` fixed.

To build and run the desktop app, just use:

`./gradlew desktop:run`

Use your arrow keys to increase the exponent or number of iterations to perform the computation.
The ghostly images use transparency to show when various points were cutoff from the image.
Their alpha values are proportional to the number of iterations it took to exceed the cutoff threshold.

