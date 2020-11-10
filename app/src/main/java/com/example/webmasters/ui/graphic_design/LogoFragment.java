package com.example.webmasters.ui.graphic_design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.webmasters.R;
import com.example.webmasters.databinding.FragmentLogosBinding;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.ShapeFactory;

public class LogoFragment extends Fragment {

    private FragmentLogosBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = FragmentLogosBinding.inflate(getLayoutInflater());

        mBinding.logoView.setSwipeListener(new LogoView.SwipeListener() {
            @Override
            public void onSwipeDown() {
                mBinding.scrollControls.setVisibility(View.GONE);
            }
            @Override
            public void onSwipeUp() {
                mBinding.scrollControls.setVisibility(View.VISIBLE);
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GraphicDesignViewModel model = new ViewModelProvider(requireActivity()).get(GraphicDesignViewModel.class);
        Logo logo = model.getLogo().getValue();
        LogoView logoView = mBinding.logoView;

        Shape[] shapes = new ShapeFactory().createShapes();
        mBinding.setVariable(BR.logo, logo);

        mBinding.spinnerShapeType.setAdapter(new ArrayAdapter<Shape>(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                shapes
        ));

        mBinding.getRoot().post(() -> {
            int xBoundary = logoView.getWidth();
            int yBoundary = logoView.getHeight();

            mBinding.seekTextX.setMax(xBoundary);
            mBinding.seekTextY.setMax(yBoundary);
            mBinding.seekShapeX.setMax(xBoundary);
            mBinding.seekShapeY.setMax(yBoundary);

            logo.setTextX(xBoundary / 2);
            logo.setTextY(yBoundary / 2);
            logo.setShapeX(xBoundary / 2);
            logo.setShapeY(yBoundary / 2);
        });

        mBinding.spinnerShapeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mBinding.getLogo().setShape(shapes[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // This should never happen...
            }
        });
    }
}