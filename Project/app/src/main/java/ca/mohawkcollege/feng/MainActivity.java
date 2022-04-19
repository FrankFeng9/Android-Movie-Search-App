package ca.mohawkcollege.feng;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import ca.mohawkcollege.feng.adapter.OnMovieCardClickListener;
import ca.mohawkcollege.feng.fragments.MovieDetailsFragment;
import ca.mohawkcollege.feng.fragments.SearchFragment;
import ca.mohawkcollege.feng.model.MovieItem;

/**
 * Main Activity of app
 */
public class MainActivity extends AppCompatActivity implements OnMovieCardClickListener {


    /**
     * on create method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, SearchFragment.class, null)
                    .commit();
        }
    }

    /**
     * Item click method
     * @param itemView item view
     * @param data  movie item data
     */
    @Override
    public void onItemClick(View itemView, MovieItem data) {
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle(data.getTitle());
        getSupportFragmentManager().beginTransaction() // push details fragment into activity
                .addSharedElement(itemView, getString(R.string.open_details_transition_name))
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, MovieDetailsFragment.newInstance(data.getImdbID()))
                .addToBackStack(MovieDetailsFragment.class.getName())
                .commit();
    }

    /**
     *  check item selected option
     * @param item menu item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager().popBackStackImmediate(); // remove details fragment
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setTitle(getString(R.string.app_name));
        return super.onOptionsItemSelected(item);
    }
}