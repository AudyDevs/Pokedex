package com.example.pokedex.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.pokedex.databinding.DialogErrorBinding

class DialogError(
    context: Context, private var onClickButtonError: () -> Unit
) : Dialog(context) {

    private val binding = DialogErrorBinding.inflate(layoutInflater)

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initListeners()
        show()
    }

    private fun initListeners() {
        binding.main.setOnClickListener {
            onClickButtonError()
            dismiss()
        }
    }
}