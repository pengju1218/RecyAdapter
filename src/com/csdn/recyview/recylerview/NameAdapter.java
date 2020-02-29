package com.csdn.recyview.recylerview;



import com.csdn.recyview.R;

import java.util.List;

public class NameAdapter  extends BaseAdapter<NameItem,BaseViewHolder>{
    public NameAdapter(int viewId, List<NameItem> dataList) {
        super(viewId, dataList);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, int layoutId, NameItem item) {

        viewHolder.setText(R.id.ids_name,item.getName());
    }
}
