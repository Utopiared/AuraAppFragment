package mx.unam.tic.asistentemoda

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import mx.unam.tic.asistentemoda.databinding.FragmentLoginBinding


private const val VALID_EMAIL = "user@tic.unam.mx"
private const val VALID_PASSWORD = "d1pl0m0v1l35?"

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fieldWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) = updateLoginButtonState()
        }
        binding.emailEditText.addTextChangedListener(fieldWatcher)
        binding.passwordEditText.addTextChangedListener(fieldWatcher)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text?.toString().orEmpty()
            val password = binding.passwordEditText.text?.toString().orEmpty()
            handleLogin(email, password)
        }
    }

    private fun updateLoginButtonState() {
        val email = binding.emailEditText.text?.toString().orEmpty()
        val password = binding.passwordEditText.text?.toString().orEmpty()
        binding.loginButton.isEnabled = email.isNotBlank() && password.isNotBlank()
    }


    private fun handleLogin(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            showToast(R.string.error_empty_fields)
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast(R.string.error_invalid_email)
            return
        }

        if (email == VALID_EMAIL && password == VALID_PASSWORD) {
            navigateToWelcome(email)
        } else {
            showToast(R.string.error_invalid_credentials)
        }
    }

    private fun showToast(messageResId: Int) {
        Toast.makeText(requireContext(), getString(messageResId), Toast.LENGTH_SHORT).show()
    }


    private fun navigateToWelcome(email: String) {
        val welcomeFragment = WelcomeFragment.newInstance(email)

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_fade_slide_up,
                R.anim.exit_fade_slide_left,
                R.anim.pop_enter_fade_slide_right,
                R.anim.pop_exit_fade_slide_down
            )
            .replace(R.id.fragment_container, welcomeFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
