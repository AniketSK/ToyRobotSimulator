package com.aniketkadam.toyrobotsimulator.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aniketkadam.toyrobotsimulator.databinding.ActivityMainBinding;

/**
 * Activity that handles common operations for all activities and enforces using databinding and presenters.
 */

public abstract class BaseActivity<T extends BasePresenter, V extends ActivityMainBinding> extends AppCompatActivity {
    protected T presenter;
    protected V binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( presenter == null ) {
            presenter = getPresenter();
        }
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
    }

    protected abstract T getPresenter();

    protected abstract @LayoutRes
    int getLayoutRes();
}
