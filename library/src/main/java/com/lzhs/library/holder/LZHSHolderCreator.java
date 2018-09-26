package com.lzhs.library.holder;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface LZHSHolderCreator<VH extends LZHSViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
