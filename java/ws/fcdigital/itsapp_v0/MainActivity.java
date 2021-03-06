package ws.fcdigital.itsapp_v0;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Array of options --> ArrayAdapter --> ListView

// List view: {views: events.xml}

public class MainActivity extends ActionBarActivity implements ws.fcdigital.itsapp_v0.NavigationDrawerFragment.NavigationDrawerCallbacks {

    private List<EventItsApp> myEvents = new ArrayList<EventItsApp>();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        populateEventList();
        populateListView();

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    // Populate Events List
    private void populateEventList() {
        myEvents.add(new EventItsApp("O Chocolate em", "Campo Pequeno", "Food & Beverage", "05", "Fev", "20:00", R.drawable.bg0));
        myEvents.add(new EventItsApp("O Chocolate em", "Campo Grande", "Food", "06", "Fev", "21:00", R.drawable.bg1));
        myEvents.add(new EventItsApp("O Chocolate em", "Entrecampos", "Beverage", "07", "Fev", "22:00", R.drawable.bg2));
    }

    private void populateListView() {

        // Create Event List
        ListView list = (ListView) findViewById(R.id.eventListView);

        // Add Flipper to the Event List header
        View headerView = View.inflate(this, R.layout.view_flipper_main, null);
        list.addHeaderView(headerView);

        // Set Adapter
        ArrayAdapter<EventItsApp> adapter = new MyListAdapter();
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<EventItsApp> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.event_view, myEvents);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Make sure we have a view to work with (may have been given null)
            View eventView = convertView;
            if (eventView == null) {
                eventView = getLayoutInflater().inflate(R.layout.event_view, parent, false);
            }

            // Find the car to work with.
            EventItsApp currentEvent = myEvents.get(position);

            // Fill the view
            ImageView imageView = (ImageView) eventView.findViewById(R.id.event_picture);
            imageView.setImageResource(currentEvent.getPicture());

            // Name:
            TextView nameText = (TextView) eventView.findViewById(R.id.event_name);
            nameText.setText(currentEvent.getName());

            // Local:
            TextView localText = (TextView) eventView.findViewById(R.id.event_local);
            localText.setText("" + currentEvent.getLocal());

            // Type:
            TextView typeText = (TextView) eventView.findViewById(R.id.event_type);
            typeText.setText(currentEvent.getType());

            // Day:
            TextView dayText = (TextView) eventView.findViewById(R.id.event_day);
            dayText.setText(currentEvent.getDay());
            dayText.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            // Month:
            TextView monthText = (TextView) eventView.findViewById(R.id.event_month);
            monthText.setText(currentEvent.getMonth());
            monthText.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            // Hour:
            TextView hourText = (TextView) eventView.findViewById(R.id.event_hour);
            hourText.setText(currentEvent.getHour());

            return eventView;
        }
    }

//    private void registerClickCallback() {
//        ListView list = (ListView) findViewById(R.id.eventsListView);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
//
//                Event clickedEvent = myEvents.get(position);
//                String message = "You clicked #"
//                        + position
//                        + " which is event: "
//                        + clickedEvent.getName();
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
//            }
//        });
//    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        super.onOptionsItemSelected(item);
//        switch(item.getItemId()) {
//            case R.id.action_settings:
//                settingsMenuItem();
//                break;
//            case R.id.action_location:
//                locationMenuItem();
//                break;
//        }
//        return true;
//    }
//
//    private void settingsMenuItem() {
//
//    }
//
//    private void locationMenuItem () {
//
//    }
}
