package com.g2452.demo.base;

import com.g2452.demo.contract.Icontract;

public interface BaseMVP<M extends Icontract.Model, V extends Icontract.View, P extends BasePersenter> {

    M creatModel();

    V creatView();

    P creatPersenter();


}
