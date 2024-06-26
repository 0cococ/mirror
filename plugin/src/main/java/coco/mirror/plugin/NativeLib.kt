package coco.mirror.plugin

class NativeLib {

    /**
     * A native method that is implemented by the 'plugin' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'plugin' library on application startup.
        init {
            System.loadLibrary("plugin")
        }
    }
}