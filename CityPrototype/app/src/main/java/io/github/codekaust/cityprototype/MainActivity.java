package io.github.codekaust.cityprototype;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.codekaust.cityprototype.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private CityMatrix cityMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        cityMatrix = new CityMatrix();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.setMatrix(cityMatrix.getMatrix());
    }
}