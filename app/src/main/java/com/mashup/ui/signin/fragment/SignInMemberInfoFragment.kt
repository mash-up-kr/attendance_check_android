package com.mashup.ui.signin.fragment

import androidx.navigation.fragment.findNavController
import com.mashup.R
import com.mashup.base.BaseFragment
import com.mashup.databinding.FragmentSignInMemberInfoBinding
import com.mashup.ui.extensions.setFailedUiOfTextField
import com.mashup.ui.extensions.setSuccessUiOfTextField
import com.mashup.ui.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInMemberInfoFragment : BaseFragment<FragmentSignInMemberInfoBinding>() {

    private lateinit var viewModel: SignInViewModel

    override val layoutId: Int
        get() = R.layout.fragment_sign_in_member_info

    override fun initViews() {
        initTextField()
        initButton()
    }

    private fun initTextField() {
        viewBinding.textFieldId.run {
            addOnTextChangedListener { text ->
                if (validationId(text)) {
                    setSuccessUiOfTextField()
                } else {
                    setFailedUiOfTextField()
                }
            }
        }

        viewBinding.textFieldPwd.run {
            addOnTextChangedListener { text ->
                if (validationPwd(text)) {
                    setSuccessUiOfTextField()
                } else {
                    setFailedUiOfTextField()
                }
            }
        }
    }

    private fun initButton() {
        viewBinding.btnSignIn.setOnButtonClickListener {
            findNavController().navigate(R.id.action_signInMemberInfoFragment_to_signInCodeFragment)
        }
    }
}