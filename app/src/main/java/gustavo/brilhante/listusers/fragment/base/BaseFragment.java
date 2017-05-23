package gustavo.brilhante.listusers.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import gustavo.brilhante.listusers.R;
import gustavo.brilhante.listusers.utils.ScreenUtils;

/**
 * Created by yann.braga on 13/12/2016.
 */
public class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public void goTo(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(ScreenUtils.isTablet(getActivity())){
            fragmentTransaction.replace(R.id.containerRight, fragment);
        }else {
            fragmentTransaction.replace(R.id.containerLeft, fragment);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void goBack(){
        if(ScreenUtils.isTablet(getActivity())){

        }else {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        }

    }


}
