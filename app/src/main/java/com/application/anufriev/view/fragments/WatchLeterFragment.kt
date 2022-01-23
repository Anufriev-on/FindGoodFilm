package com.application.anufriev.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.anufriev.R
import com.application.anufriev.utils.AnimationHelper
import com.application.anufriev.databinding.FragmentWatchLeterBinding

//import kotlinx.android.synthetic.main.fragment_watch_leter.*

class WatchLeterFragment : Fragment() {

    private lateinit var binding: FragmentWatchLeterBinding

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {

        binding = FragmentWatchLeterBinding.inflate(inflater, container, false)
        return binding.root
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.watchLeterFragmentRoot, requireActivity(), 2)

    }
}