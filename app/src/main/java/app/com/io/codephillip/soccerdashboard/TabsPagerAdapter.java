package app.com.io.codephillip.soccerdashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
//		case 0:
//			return new Predictions();
//		case 1:
//			return new Tables();
//		case 2:
//			return new Fixtures();

            //removed the predictions tab as I build the predictions algorithm for version 2
//            case 0:
//                return new Predictions();
            case 0:
                return new Tables();
            case 1:
                return new Fixtures();




		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 2;
//		return 3;
	}

}
