package mx.unam.tic.asistentemoda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import mx.unam.tic.asistentemoda.databinding.FragmentWelcomeBinding


private const val ARG_EMAIL = "arg_email"


class WelcomeFragment : Fragment() {

    companion object {

        fun newInstance(email: String): WelcomeFragment {
            return WelcomeFragment().apply {
                arguments = bundleOf(ARG_EMAIL to email)
            }
        }
    }

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString(ARG_EMAIL).orEmpty()


        binding.welcomeMessage.text = getString(R.string.welcome_message_personalized, email)
        binding.btnIniciarAsistencia.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
