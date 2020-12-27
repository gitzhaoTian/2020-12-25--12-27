package com.example.day12_24.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.day12_24.R;
import com.example.day12_24.TextHotAdapter;
import com.example.day12_24.adapter.HotGoodsAdapter;
import com.example.day12_24.adapter.MainGridAdapter;
import com.example.day12_24.adapter.MainLinearAdapter;
import com.example.day12_24.adapter.NewGoodsAdapter;
import com.example.day12_24.adapter.SmartAdapter;
import com.example.day12_24.adapter.TextAdapter;
import com.example.day12_24.adapter.TextDayAdapter;
import com.example.day12_24.api.ApiService;
import com.example.day12_24.bean.BanBean;
import com.example.day12_24.bean.SmartBean;
import com.example.day12_24.utils.URLConstant;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private RecyclerView rv_home;
    private DelegateAdapter adapter;
    private LinearLayoutHelper linearLayoutHelper;
    private ArrayList<Integer> list;
    private VirtualLayoutManager virtualLayoutManager;
    private ArrayList<SmartBean.DataBean.BrandListBean> brandListBeans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
        initData();
        initNewDay();
        initHotGoods();
        initSmart();
    }

    private void initNewDay() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper(5);
        String title = "周一周四·新品首发";
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setSpanCount(2);// 设置每行多少个网格
        ArrayList<SmartBean.DataBean.NewGoodsListBean> list1 = new ArrayList<>();
        new Retrofit.Builder()
                .baseUrl(ApiService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getSmart("index")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SmartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SmartBean smartBean) {
                        list1.addAll(smartBean.getData().getNewGoodsList());
                        NewGoodsAdapter newGoodsAdapter = new NewGoodsAdapter(list1,gridLayoutHelper,getContext());
                        TextDayAdapter textAdapter = new TextDayAdapter(getContext(),layoutHelper,title);
                        adapter.addAdapter(textAdapter);
                        adapter.addAdapter(newGoodsAdapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initHotGoods() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper(5);
        String title = "人气推荐";
        LinearLayoutHelper helper = new LinearLayoutHelper(5);
        ArrayList<SmartBean.DataBean.HotGoodsListBean> hotGoodsListBeans = new ArrayList<>();
        new Retrofit.Builder()
                .baseUrl(ApiService.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getSmart("index")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<SmartBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull SmartBean smartBean) {
                hotGoodsListBeans.addAll(smartBean.getData().getHotGoodsList());
                HotGoodsAdapter hotGoodsAdapter = new HotGoodsAdapter(helper,hotGoodsListBeans,getContext());
                TextHotAdapter textHotAdapter = new TextHotAdapter(getContext(), layoutHelper, title);
                adapter.addAdapter(textHotAdapter);
                adapter.addAdapter(hotGoodsAdapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initSmart() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper(5);
        String title = "品牌制造商直供";
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数
        // 公共属性
//        gridLayoutHelper.setItemCount(6);// 设置布局里Item个数
//        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
////        gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
//        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
//// gridLayoutHelper特有属性（下面会详细说明）
//        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});//设置每行中 每个网格宽度 占 每行总宽度 的比例
//        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
//        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
//        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(2);// 设置每行多少个网格

        brandListBeans = new ArrayList<>();
        new Retrofit.Builder()
                .baseUrl(ApiService.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getSmart("index")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SmartBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SmartBean smartBean) {
                        brandListBeans.addAll(smartBean.getData().getBrandList());
                        SmartAdapter smartAdapter = new SmartAdapter(gridLayoutHelper,brandListBeans,getContext());
                        TextAdapter textAdapter = new TextAdapter(getContext(),layoutHelper,title);
                        adapter.addAdapter(textAdapter);
                        adapter.addAdapter(smartAdapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG", "onError: 网络异常:"+e.getMessage());
                        Toast.makeText(getContext(), "网络异常:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initData() {
//        new Retrofit.Builder()
//                .baseUrl(URLConstant.BASEURL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService.class)
//                .getGirls("index")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BanBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull BanBean banBean) {
//                        list.addAll(banBean.getData().getBanner());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Toast.makeText(getContext(), "网络异常:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e("TAG", "网络异常:"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        Log.e("TAG", "initData: size"+list.size());
        linearLayoutHelper = new LinearLayoutHelper(5);
        MainLinearAdapter mainLinearAdapter = new MainLinearAdapter(linearLayoutHelper, list, getContext());
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数
        // 公共属性
        gridLayoutHelper.setItemCount(6);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
//        gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
// gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.ic_jujia);
        integers.add(R.drawable.ic_canchu);
        integers.add(R.drawable.ic_peijian);
        integers.add(R.drawable.ic_dress);
        integers.add(R.drawable.ic_game);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("居家");
        strings.add("餐厨");
        strings.add("配件");
        strings.add("服装");
        strings.add("志趣");
        MainGridAdapter mainGridAdapter = new MainGridAdapter(gridLayoutHelper,integers,strings,getContext());
        adapter.addAdapter(mainLinearAdapter);
        adapter.addAdapter(mainGridAdapter);
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        rv_home = view.findViewById(R.id.rv_home);
        virtualLayoutManager = new VirtualLayoutManager(getContext());
        rv_home.setLayoutManager(virtualLayoutManager);
        list = new ArrayList<>();
//        GirlsPresenterImpl girlsPresenter = new GirlsPresenterImpl(this);
//        girlsPresenter.get();
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        rv_home.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0,10);
        adapter = new DelegateAdapter(virtualLayoutManager, true);
        rv_home.setAdapter(adapter);
//        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
//        // 公共属性
//        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
//        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
//        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
//        MainSingleAdapter mainSingleAdapter = new MainSingleAdapter(singleLayoutHelper);
    }

//    @Override
//    public void getGirls(BanBean girlsBean) {
//        list.addAll(girlsBean.getData().getBanner());
//        mainLinearAdapter.notifyDataSetChanged();
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onFail(String error) {
//        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
//    }
}