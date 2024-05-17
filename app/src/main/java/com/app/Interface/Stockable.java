package com.app.Interface;

import com.app.AbstractClass.Plant;
import com.app.Exception.IndexOutOfRangeException;
import com.app.Exception.InvalidDeckSwapException;
import com.app.Exception.InvalidInventorySwapException;
import com.app.Exception.InvalidPlacingToTileException;
import com.app.Exception.InvalidStoringException;
import com.app.Exception.RemoveNullException;

public interface Stockable {
    public void addItem(Plant plant) throws InvalidStoringException;
    public void removeItem(Plant plant)throws RemoveNullException;
    public void switchPlacement(int index1, int index2)throws InvalidInventorySwapException, IndexOutOfRangeException;
}
