package gustavo.brilhante.listusers.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gustavo.brilhante.listusers.R;
import gustavo.brilhante.listusers.adapter.UserAdapter;
import gustavo.brilhante.listusers.constants.Config;
import gustavo.brilhante.listusers.constants.Constants;
import gustavo.brilhante.listusers.fragment.base.BaseFragment;
import gustavo.brilhante.listusers.listeners.UserAdapterListener;
import gustavo.brilhante.listusers.models.CacheInfo;
import gustavo.brilhante.listusers.models.Data;
import gustavo.brilhante.listusers.models.Info;
import gustavo.brilhante.listusers.models.Result;
import gustavo.brilhante.listusers.requests.MakeRequest;
import gustavo.brilhante.listusers.utils.AnimUtils;
import gustavo.brilhante.listusers.utils.DateUtils;
import gustavo.brilhante.listusers.utils.ScreenUtils;
import io.realm.Realm;
import okhttp3.Response;


@EFragment(R.layout.fragment_user_list)
public class UserListFragment extends BaseFragment implements UserAdapterListener {

    @ViewById
    LinearLayout listLayout, errorMessageLayout, loadingLayout;

    @ViewById
    CircularProgressView progressView;

    @ViewById
    RecyclerView recyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    public boolean isLoadingSpinning = false;

    UserAdapter adapter;

    Gson gson;

    Data data;

    Realm realm;

    @AfterViews
    void afterViews(){
        gson = new Gson();

        realm = Realm.getDefaultInstance();

        if(data==null){
            realm.beginTransaction();
            List<Result> realmList = realm.where(Result.class).findAll();
            CacheInfo cacheInfo = realm.where(CacheInfo.class).findFirst();
            Info info = realm.where(Info.class).findFirst();
            realm.commitTransaction();
            boolean timeExpired;
            if(cacheInfo!=null) {
                //cache de dados inspira no tempo definido pela variável CACHE_EXPIRE_TIME
                timeExpired = DateUtils.checkDifferenceTimeInMinutes(cacheInfo.getDownloadedTime(), Config.CACHE_EXPIRE_TIME);
            }else{
                timeExpired = true;
            }
            if(realmList!=null && cacheInfo!=null && info!=null && realmList.size()>0 && !timeExpired ){
                setLoading(false, false);
                data = new Data();
                data.results = new ArrayList<Result>(realmList);
                data.info = info;
                setRecyclerViewContent();
            }
            else{
                setLoading(true, false);
                downloadContent();
            }
        }else{
            setLoading(false, false);
            setRecyclerViewContent();
        }



    }

    @Background(serial = "loadPage")
    void downloadContent(){
        String url = Constants.getUserUrl(100);

        try{
            Response response = MakeRequest.get(url, null);
            if(response.isSuccessful()){
                cleanRealm();
                String resp = response.body().string();

                data = gson.fromJson(resp, Data.class);

                for (int i=0; i<data.results.size(); i++){
                    pushToRealm(data.results.get(i));
                }
                CacheInfo cacheInfo = new CacheInfo();
                cacheInfo.setDownloadedTime(DateUtils.getCurrentDateTime());
                pushInfoToRealm(cacheInfo, data.info);

                setRecyclerViewContent();

                setLoading(false, false);

            }else{
                setLoading(false, true);
            }

        }
        catch (JsonParseException e) {

            setLoading(false, true);

        } catch (IOException e) {
            e.printStackTrace();

            setLoading(false, true);

        }
    }

    @UiThread
    void pushToRealm(Result result){
        realm.beginTransaction();
        realm.copyToRealm(result);
        realm.commitTransaction();
    }

    @UiThread
    void pushInfoToRealm(CacheInfo cacheInfo, Info info){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cacheInfo);
        realm.copyToRealmOrUpdate(info);
        realm.commitTransaction();
    }


    @UiThread
    void cleanRealm(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    @UiThread
    void setRecyclerViewContent(){
        adapter = new UserAdapter(data, getContext());
        adapter.setListener(this);
        if(ScreenUtils.isTablet(getContext()))adapter.setSelectable(true);
        recyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Click(R.id.errorMessageTextView)
    void onErrorMessageClick(){
        setLoading(true, false);
        data = new Data();
        downloadContent();
    }


    @UiThread
    void setLoading(boolean isLoading, boolean error) {
        if (isLoading && loadingLayout.getVisibility() == View.GONE) {
            isLoadingSpinning = isLoading;
            loadingLayout.setVisibility(View.VISIBLE);
            progressView.startAnimation();
            listLayout.setVisibility(View.GONE);
            errorMessageLayout.setVisibility(View.GONE);
            AnimUtils.fadeInView(loadingLayout);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        } else if (!isLoading && loadingLayout.getVisibility() == View.VISIBLE && !error) {
            isLoadingSpinning = isLoading;
            loadingLayout.setVisibility(View.GONE);
            progressView.stopAnimation();
            listLayout.setVisibility(View.VISIBLE);
            AnimUtils.fadeInView(listLayout);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else if (!isLoading && loadingLayout.getVisibility() == View.VISIBLE && error) {
            isLoadingSpinning = isLoading;
            loadingLayout.setVisibility(View.GONE);
            progressView.stopAnimation();
            errorMessageLayout.setVisibility(View.VISIBLE);
            AnimUtils.fadeInView(errorMessageLayout);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    public void onUserClick(Result result) {
        DetailFragment_ fragment = new DetailFragment_();
        Bundle args = new Bundle();
        args.putParcelable(DetailFragment.RESULT_SERIALIZABLE, result);
        fragment.setArguments(args);
        goTo(fragment);
    }
}
