package com.csdn.recyview.recylerview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseAdapter<E extends BaseEnty, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private static final int NONE = 900;

    private List<E> mDataList = new ArrayList<>();//数据集合
    private SparseArrayCompat<Integer> mItemTypes = new SparseArrayCompat();    //类型集合
    private boolean multyItem = false;
    private int rootIDd;

    //头集合 尾结合
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    public BaseAdapter(List<E> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);




    }


    public BaseAdapter(@IdRes int viewId, List<E> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        multyItem = false;
        rootIDd = viewId;
    }


    public void addItemType(int type, @IdRes int viewId) {
        multyItem = true;
        mItemTypes.put(type,viewId);



    }


    /**
     * 添加头部方法
     *
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加尾部方法
     *
     * @param view
     */
    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("eeee",  ""+ "----------"+position);

        if (position < mHeaderViews.size()) {
            return mHeaderViews.keyAt(position);
        } else if (position >= mHeaderViews.size() + mDataList.size()) {
            return mFootViews.keyAt(position - mHeaderViews.size() - mDataList.size());
        } else if (multyItem && position >= mHeaderViews.size()) {
           Log.i("eeee", mItemTypes.keyAt(position - mHeaderViews.size()) + "----------"+position);


           int current=position-mHeaderViews.size();
            return mDataList.get(current).getType();
        } else {
            return NONE;
        }

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


         Log.i("eeee",viewType+"");
        if (mHeaderViews.get(viewType) != null) {
            return BaseViewHolder.getInstance(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            return BaseViewHolder.getInstance(mFootViews.get(viewType));
        } else if (mItemTypes.get(viewType) != null) {
             Log.i("eeee",mItemTypes.get(viewType)+"");
            int viewId = mItemTypes.get(viewType);
            Context context = viewGroup.getContext();
            View view = LayoutInflater.from(context).inflate(viewId, viewGroup, false);
            return BaseViewHolder.getInstance(view);
        } else if (!multyItem) {
            Context context = viewGroup.getContext();
            View view = LayoutInflater.from(context).inflate(rootIDd, viewGroup, false);
            return BaseViewHolder.getInstance(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);

        if (mHeaderViews.get(viewType) != null) {
            // convert(viewHolder,mHeaderViews.get(viewType).getId(),mDataList.get(position));
        } else if (mFootViews.get(viewType) != null) {
            // convert(viewHolder,mFootViews.get(viewType).getId(),mDataList.get(position));
        } else if (mItemTypes.get(viewType) != null) {
            convert(viewHolder, mItemTypes.get(viewType), mDataList.get(position - mHeaderViews.size()));
        } else if (!multyItem) {
            convert(viewHolder, 0, mDataList.get(position - mHeaderViews.size()));
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mDataList.size() + mFootViews.size();
    }

    /**
     * 设置类型
     *
     * @param item 数据
     * @return 返回的布局类型
     */
    protected int getItemType(E item) {

        if (multyItem) {
            return item.getType();
        } else {
            return NONE;
        }
    }

    protected abstract void convert(BaseViewHolder viewHolder, int layoutId, E item);


}
