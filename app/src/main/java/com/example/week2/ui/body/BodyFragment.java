package com.example.week2.ui.body;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.week2.databinding.FragmentBodyBinding;

public class BodyFragment extends Fragment {

    private FragmentBodyBinding binding;  // Updated binding class

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Use FragmentBodyBinding instead of FragmentDashboardBinding
        binding = FragmentBodyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Find the TextViews by their IDs
        TextView someIdTextView1 = binding.inbody;
        TextView someIdTextView2 = binding.workout;

        // You can modify the TextViews or the layout as needed
        // For example, setting text or changing properties

        // Example:
        someIdTextView1.setText("Hello, World!");
        someIdTextView2.setText("Another TextView");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
