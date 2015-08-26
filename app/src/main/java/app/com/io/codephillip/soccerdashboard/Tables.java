package app.com.io.codephillip.soccerdashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tables extends Fragment {
    private String[] tableArray;
    private ListView tableList;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tables_layout, container, false);

        tableList = (ListView) view.findViewById(R.id.lv_tables);

        tableArray = new String[]{
                "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
        };

        ArrayAdapter <String>  adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, tableArray
                );
        tableList.setAdapter(adapter);

		return view;
	}
}
