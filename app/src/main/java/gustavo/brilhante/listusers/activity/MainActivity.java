package gustavo.brilhante.listusers.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import gustavo.brilhante.listusers.R;
import gustavo.brilhante.listusers.fragment.UserListFragment_;
import gustavo.brilhante.listusers.utils.ScreenUtils;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public boolean isTablet = false;

    @ViewById
    FrameLayout containerLeft, containerRight;

    @AfterViews
    void afterViews(){

        configScreen();

        UserListFragment_ fragment = new UserListFragment_();
        setContainerLeft(fragment);

    }

    void configScreen(){
        isTablet = ScreenUtils.isTablet(this);
        if(isTablet){
            containerRight.setVisibility(View.VISIBLE);
        }else{
            containerRight.setVisibility(View.GONE);
        }
    }

    private void setContainerLeft(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLeft, fragment, "MENU").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    @Override
    public void onBackPressed() {
        if(isTablet){
            finish();
        }else {
            super.onBackPressed();
        }

    }
}
