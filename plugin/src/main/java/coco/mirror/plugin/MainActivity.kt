package coco.mirror.plugin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coco.mirror.plugin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = NativeLib().stringFromJNI()
    }
}