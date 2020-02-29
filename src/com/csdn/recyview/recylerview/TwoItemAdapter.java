package com.csdn.recyview.recylerview;



import com.csdn.recyview.R;

import java.util.List;

public class TwoItemAdapter extends BaseAdapter<NameItem,BaseViewHolder>{

    public TwoItemAdapter(List<NameItem> dataList) {
        super(dataList);
        addItemType(BaseEnty.AA,R.layout.name_item);
        addItemType(BaseEnty.BB,R.layout.layout_item);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, int layoutId, NameItem item) {

        if(item.getType()==BaseEnty.AA){
            viewHolder.setText(R.id.ids_name,item.getName());
        }else if(item.getType()==BaseEnty.BB){
            viewHolder.setText(R.id.ids_name,item.getName());
        }

    }
}
