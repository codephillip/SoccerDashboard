package app.com.io.codephillip.soccerdashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fixtures extends Fragment {
    private ListView fixtureList;
    private String[] fixtureArray;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fixtures_layout, container, false);

        fixtureList = (ListView) view.findViewById(R.id.lv_fixtures);

        fixtureArray = new String[]{
                "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, fixtureArray
                );

        fixtureList.setAdapter(adapter);

		return view;
	}
}
