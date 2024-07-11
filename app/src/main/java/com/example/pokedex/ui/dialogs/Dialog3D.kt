package com.example.pokedex.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.Dialog3dBinding

class Dialog3D(
    context: Context,
    private val image3D: String
) : Dialog(context) {

    private val binding = Dialog3dBinding.inflate(layoutInflater)

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initUI()
        initListeners()
        show()
    }

    private fun initUI() {
        Glide.with(context)
            .load(image3D)
            .into(binding.ivIcon)
    }

    private fun initListeners() {
        binding.main.setOnClickListener {
            dismiss()
        }
    }
}