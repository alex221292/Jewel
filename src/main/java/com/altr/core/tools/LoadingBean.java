package com.altr.core.tools;


import com.altr.core.data.service.CommonViewer;
import com.altr.core.data.service.impl.CommonViewerBean;

public class LoadingBean {

    public static CommonViewer commonViewer() throws IllegalAccessException, InstantiationException {
        return CommonViewerBean.get();
    }

}
