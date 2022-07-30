package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityMainBinding;
import com.ufscar.dc.appbibliotecadejogos.fragments.CollectionFragment;
import com.ufscar.dc.appbibliotecadejogos.fragments.ExploreFragment;
import com.ufscar.dc.appbibliotecadejogos.fragments.HomeFragment;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Tela inicial de quando o aplicativo é aberto
        replaceFragment(new HomeFragment(), getString(R.string.home_title));

        // Navegação do app pela barra inferior
        binding.bottomNavigationMenu.setOnItemSelectedListener(item -> {
            String title;
            int id = item.getItemId();

            if (id == R.id.home) {
                title = getString(R.string.home_title);
                replaceFragment(new HomeFragment(), title);
            }
            else if (id == R.id.explore) {
                title = getString(R.string.explore_title);
                replaceFragment(new ExploreFragment(), title);
            }
            else if (id == R.id.collection) {
                title = getString(R.string.collection_title);
                replaceFragment(new CollectionFragment(), title);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

}