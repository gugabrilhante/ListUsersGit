package gustavo.brilhante.listusers.adapter.holders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gustavo.brilhante.listusers.R;
import gustavo.brilhante.listusers.models.Result;
import gustavo.brilhante.listusers.utils.DateUtils;

/**
 * Created by Gustavo on 22/05/17.
 */

public class UserHolder extends RecyclerView.ViewHolder {

    ImageView profileImageView;

    TextView nomeTextView, idadeTextView, genderTextView, natTextView;

    CardView rootView;

    Context context;

    public Result result;

    public UserHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;

        rootView = (CardView) itemView.findViewById(R.id.rootView);

        profileImageView = (ImageView) itemView.findViewById(R.id.profileImageView);

        nomeTextView = (TextView) itemView.findViewById(R.id.nomeTextView);
        idadeTextView = (TextView) itemView.findViewById(R.id.idadeTextView);
        genderTextView = (TextView) itemView.findViewById(R.id.genderTextView);
        natTextView = (TextView) itemView.findViewById(R.id.natTextView);

    }

    public void bind(Result result){
        this.result = result;
        nomeTextView.setText(result.name.first);
        String age = DateUtils.getAgeFromDate(result.dob);
        if(!age.isEmpty())idadeTextView.setText(age+ " anos");
        genderTextView.setText(result.gender);
        natTextView.setText(result.nat);

        Picasso.with(context).load(result.picture.thumbnail).into(profileImageView);
        changeLayoutSelection();

    }



    public void changeLayoutSelection(){
        if(result.isSelected) {
            rootView.setBackgroundColor(context.getResources().getColor(R.color.colorSelection));
            nomeTextView.setTextColor(context.getResources().getColor(R.color.white));
            idadeTextView.setTextColor(context.getResources().getColor(R.color.white));
            genderTextView.setTextColor(context.getResources().getColor(R.color.white));
            natTextView.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            rootView.setBackgroundColor(context.getResources().getColor(R.color.white));
            nomeTextView.setTextColor(context.getResources().getColor(R.color.standartGray));
            idadeTextView.setTextColor(context.getResources().getColor(R.color.standartGray));
            genderTextView.setTextColor(context.getResources().getColor(R.color.standartGray));
            natTextView.setTextColor(context.getResources().getColor(R.color.standartGray));
        }
    }

    public static UserHolder build(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_user, parent, false);
        UserHolder holder = new UserHolder(view, parent.getContext());
        return holder;
    }

}
