LWJGL 2.9.4 - Lightweight Java Game Library Fork with armv7l support (No OpenAL yet) 
======
This is a fork of LWJGL 2.9.4. I will be making edits to a couple things along the way of playing around with my ARM laptop.


The Lightweight Java Game Library (LWJGL) is a solution aimed directly at professional and amateur Java programmers alike to enable commercial quality games to be written in Java. 
LWJGL provides developers access to high performance crossplatform libraries such as OpenGL (Open Graphics Library), OpenCL (Open Computing Language) and OpenAL (Open Audio Library) allowing for state of the art 3D games and 3D sound.
Additionally LWJGL provides access to controllers such as Gamepads, Steering wheel and Joysticks.
All in a simple and straight forward API.

Bugs/Suggestions: [https://github.com/l3eta/lwjgl/issues](https://github.com/l3eta/lwjgl/issues)

Compilation
-----------

LWJGL requires a JDK and Ant installed to compile, as well as your platforms native compiler to compile the JNI.

* ant generate-all
* ant compile
* ant compile_native
