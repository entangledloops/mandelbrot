# Mandelbrot Renderer

![Mandelbrot](https://github.com/entangledloops/mandelbrot/blob/master/img/mandelbrot.png)

To build and run the desktop app, just use:

`./gradlew desktop:run`

Use your arrow keys to increase the exponent or number of iterations to perform the computation.
The ghostly images use transparency to show when various points were cutoff from the image.
Their alpha values are proportional to the number of iterations it took to exceed the cutoff threshold.

