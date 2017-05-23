package gustavo.brilhante.listusers.fragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import gustavo.brilhante.listusers.R;
import gustavo.brilhante.listusers.models.Result;
import gustavo.brilhante.listusers.utils.DateUtils;
import gustavo.brilhante.listusers.utils.ScreenUtils;
import gustavo.brilhante.listusers.utils.StringUtils;


@EFragment(R.layout.fragment_detail)
public class DetailFragment extends Fragment {

    public static final String RESULT_SERIALIZABLE = "result_arg";

    @FragmentArg(RESULT_SERIALIZABLE)
    Result result;

    @ViewById
    TextView nomeTextView, sexoTextView, idadeTextView, enderecoTextView, telefoneTextView, celularTextView, emailTextView, dataRegistroTextView;

    @ViewById
    ImageView profileImageView;

    @ViewById
    LinearLayout rootView;

    boolean isTablet;

    @AfterViews
    void afterViews(){

        isTablet = ScreenUtils.isTablet(getContext());

        if(result!=null){
            if(result.picture!=null && result.picture.large!=null)
                Picasso.with(getContext()).load(result.picture.large).into(profileImageView);


            initTextViews();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            if(!isTablet)rootView.setOrientation(LinearLayout.HORIZONTAL);
        }else{
            if(!isTablet)rootView.setOrientation(LinearLayout.VERTICAL);
        }
    }

    void initTextViews(){
        if(result.name!=null) {
            nomeTextView.setText(StringUtils.getCompleteName(result.name));
        }
        if(result.gender!=null)sexoTextView.setText(result.gender);
        if(result.dob!=null) {
            String age = DateUtils.getAgeFromDate(result.dob);
            if (!age.isEmpty()) idadeTextView.setText(age + " anos");
        }
        if(result.location!=null){
            enderecoTextView.setText(StringUtils.getCompleteAddress(result.location));
        }
        if(result.phone!=null){
            telefoneTextView.setText(result.phone);
        }
        if(result.cell!=null){
            celularTextView.setText(result.cell);
        }
        if (result.email!=null){
            emailTextView.setText(result.email);
        }
        if(result.registered!=null){
            dataRegistroTextView.setText(DateUtils.getFormattedDateStr(result.registered));
        }
    }

}
