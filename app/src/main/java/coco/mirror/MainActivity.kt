package coco.mirror


import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coco.mirror.core.Env
import coco.mirror.core.manger.DexManger
import coco.mirror.core.manger.NativeManger
import coco.mirror.core.manger.PackageManager
import coco.mirror.core.manger.ResourcesManger
import coco.mirror.core.utils.APP_DIRECTORY

import coco.mirror.core.utils.APP_THAT_DIRECTORY
import coco.mirror.core.utils.APP_THAT_LIB_DIRECTORY
import coco.mirror.core.utils.APP_THAT_THIS
import coco.mirror.core.utils.ClassUtils
import coco.mirror.core.utils.FilesUtils
import coco.mirror.core.utils.PackageUtils
import coco.mirror.core.utils.ZipUtils

import coco.mirror.ui.theme.MirrorTheme
import java.io.BufferedReader

import java.io.File
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Env.get().context = this
        setContentView(R.layout.activity_main)
        PackageManager.get().install("/storage/emulated/0/MT2/apks/My Application_1.0.apk")
        PackageManager.get().load()
        val s = ClassUtils.get().findClass("coco.mirror.plugin.NativeLib")
            .new()
            .getMethod("stringFromJNI")
            .invoke()

        println(PackageManager.get().getInstallPackage())
        val imageView = findViewById<ImageView>(R.id.main_show_plugin_img_iv)
        imageView.setImageDrawable(ResourcesManger.get().getRes("plugin_img"))

        setContentView(ResourcesManger.get().getLayoutView("activity_main"))

        val inputStream: InputStream = ResourcesManger.get().resources.assets.open("1.txt")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append("\n") // 添加换行符
        }
        val content: String = stringBuilder.toString()

// 打印文件内容
        println(content)

//        DexManger.get()
//        setContent {
//            MirrorTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }

//    override fun getAssets(): AssetManager {
//        return ResourcesManger.get().resources.assets ?: super.getAssets()
//    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MirrorTheme {
        Greeting("Android")
    }
}