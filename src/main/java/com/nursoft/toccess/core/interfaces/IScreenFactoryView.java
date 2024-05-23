package com.nursoft.toccess.core.interfaces;

import com.nursoft.toccess.core.impl.ScreenFactory;

public interface IScreenFactoryView {
    void setScreenToView(ScreenFactory factory);
    String getClassImplID();
}
